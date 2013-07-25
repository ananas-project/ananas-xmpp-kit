package ananas.axk2.engine.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Set;

import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppCommandStatus;
import ananas.axk2.core.XmppStatus;
import ananas.axk2.engine.XEngine;
import ananas.axk2.engine.XIOTask;
import ananas.axk2.engine.api.XEncoding;
import ananas.axk2.engine.api.XSubConnection;
import ananas.axk2.engine.api.XSuperConnection;
import ananas.axk2.engine.api.XThreadRuntime;
import ananas.axk2.engine.dom_wrapper.DOMWrapperFactoryLoader;
import ananas.axk2.engine.dom_wrapper.DOMWrapperImplementation;
import ananas.axk2.engine.dom_wrapper.DOMWrapperImplementation2;
import ananas.lib.util.ClassPropertiesLoader;
import ananas.lib.util.logging.Logger;

class ThreadRuntimeImpl implements XThreadRuntime {

	final static Logger log = Logger.Agent.getLogger();

	private final Thread _threadRx;
	private boolean _closed;
	private final XSuperConnection _parent;
	private XSubConnection _curSubConn;
	private XmppStatus _phase = XmppStatus.initial;
	/**
	 * set >0 to anti error phase( drop loop mode )
	 * */
	private int _countOnline = 1;

	private final DOMWrapperImplementation _domWrapperImpl;

	private int _current_drop_time;

	private XmppAddress _bind_jid;

	public ThreadRuntimeImpl(XSuperConnection parent) {
		this._parent = parent;
		this._threadRx = __newThread(new MyRxRunnable());
		this._domWrapperImpl = new DOMWrapperImplementation2();
	}

	class MyTxRunnable implements Runnable {
		@Override
		public void run() {
			ThreadRuntimeImpl.this.__runTx();
		}
	}

	class MyRxRunnable implements Runnable {
		@Override
		public void run() {
			try {
				ThreadRuntimeImpl.this.__runRx();
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	private Thread __newThread(Runnable runnable) {
		return new Thread(runnable);
	}

	private void __runRx() {
		final Thread threadTx = __newThread(new MyTxRunnable());
		threadTx.start();
		String bar = "========================================";
		log.trace(this + ".run(begin)");
		this.__regDOMWrappers();
		for (int index = 0; !this._closed; index++) {
			int dropTime = this.genDropTime();
			final XSubConnection subConn = new SubConnectionImpl(index, this,
					dropTime);
			this._curSubConn = subConn;
			log.trace(bar);// bar===================
			subConn.open();
			subConn.run();
			subConn.close();
			log.trace(bar);// bar===================
			if (subConn.hasOnline()) {
				this._countOnline++;
			}
			if (this._countOnline <= 0) {
				this._closed = true;
				this.setPhase(XmppStatus.error);
				break;
			}
			this._curSubConn = null;
		}
		try {
			threadTx.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.trace(this + ".run(end)");
		log.trace(bar);
	}

	private void __regDOMWrappers() {
		DOMWrapperImplementation impl = this.getDOMWrapperImplementation();
		String prefix = DOMWrapperFactoryLoader.class.getSimpleName() + "@";
		log.trace("load wrapper-ns by keys with prefix :" + prefix);
		Properties prop = (new ClassPropertiesLoader(this.getClass())).load();
		Set<Object> keys = prop.keySet();
		for (Object k : keys) {
			String key = k.toString();
			String value = prop.getProperty(key);
			if (key.startsWith(prefix)) {
				try {
					Class<?> cls = Class.forName(value);
					DOMWrapperFactoryLoader ldr = (DOMWrapperFactoryLoader) cls
							.newInstance();
					ldr.load(impl);
					log.debug("load element-wrapper with " + ldr);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
	}

	private final RunLoop _tx_runloop = new DefaultRunLoop();

	private Throwable _error;

	private void __runTx() {
		for (; !this._closed;) {
			this._tx_runloop.run(1, this);
		}
		this._tx_runloop.clear();
	}

	@Override
	public void open() {
		this._threadRx.start();
	}

	@Override
	public void close() {
		this.addTask(new XIOTask() {

			@Override
			public void onStep(XmppCommandStatus step, Throwable err) {
				String s = this + ".onStep():" + step;
				if (err == null)
					log.trace(s);
				else
					log.error(s, err);
			}

			@Override
			public void run(XThreadRuntime rt) throws IOException {

				ThreadRuntimeImpl.this._closed = true;
				XSubConnection subcon = rt.getCurrentSubConnection();
				subcon.close();

				String s = "</stream:stream>";
				OutputStream out = subcon.getOnlineOutput();
				if (out != null) {
					out.write(s.getBytes(XEncoding.theDefault));
					out.flush();
					out.close();
				} else {
					SocketAgent sock = subcon.getCurrentSocketAgent();
					sock.getInputStream().close();
					sock.getOutputStream().close();
				}
			}

			@Override
			public int getPriority() {
				return 0;
			}
		});
	}

	@Override
	public XSuperConnection getParent() {
		return this._parent;
	}

	@Override
	public XmppStatus getPhase() {
		return this._phase;
	}

	@Override
	public void setPhase(XmppStatus newPhase) {
		if (newPhase == null) {
			return;
		}
		XmppStatus oldPhase;
		synchronized (this) {
			oldPhase = this._phase;
			this._phase = newPhase;
		}
		if (!oldPhase.equals(newPhase)) {
			// log.info(this + ".onStatusChanged: " + oldPhase + " -> " +
			// newPhase);
			XEngine engine = this.getParent().getEngine();
			engine.getContext().getListener()
					.onPhaseChanged(engine, oldPhase, newPhase);
		}
	}

	@Override
	public DOMWrapperImplementation getDOMWrapperImplementation() {
		return this._domWrapperImpl;
	}

	@Override
	public int genDropTime() {
		int dt = this._current_drop_time;
		dt = this.setDropTime(dt * 2);
		return dt;
	}

	@Override
	public int setDropTime(int time) {
		final int dt_max = 3600 * 1000;
		final int dt_min = 500;
		if (time > dt_max)
			time = dt_max;
		if (time < dt_min)
			time = dt_min;
		this._current_drop_time = time;
		return time;
	}

	@Override
	public XSubConnection getCurrentSubConnection() {
		return this._curSubConn;
	}

	@Override
	public void setBind(XmppAddress addr) {
		this._bind_jid = addr;
		XEngine engine = this.getParent().getEngine();
		engine.getContext().getListener().onBind(engine, addr);
	}

	@Override
	public XmppAddress getBind() {
		return this._bind_jid;
	}

	@Override
	public boolean addTask(XIOTask task) {
		if (this._closed) {
			try {
				task.onStep(XmppCommandStatus.cancel, null);
			} catch (Exception e) {
				log.error(e);
			}
			return false;
		} else {
			if (task.getPriority() != 0) {
				this._tx_runloop.push_back(task);
			} else {
				this._tx_runloop.push_front(task);
			}
			return true;
		}
	}

	@Override
	public void setError(Throwable err) {
		this._error = err;
		this.setPhase(XmppStatus.error);
		this.close();
	}

	@Override
	public Throwable getError() {
		return this._error;
	}

}
