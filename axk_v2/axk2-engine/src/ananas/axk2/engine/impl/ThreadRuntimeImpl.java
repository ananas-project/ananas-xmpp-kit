package ananas.axk2.engine.impl;

import java.util.Properties;
import java.util.Random;
import java.util.Set;

import ananas.axk2.core.XmppStatus;
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
			ThreadRuntimeImpl.this.__runRx();
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
		log.trace(this + ".run(end)");
		log.trace(bar);
		try {
			threadTx.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
	}

	private void __runTx() {
		// TODO Auto-generated method stub

	}

	@Override
	public void open() {
		this._threadRx.start();
	}

	@Override
	public void close() {
		// close myself
		this._closed = true;
		// close my child
		XSubConnection subConn = this._curSubConn;
		if (subConn != null) {
			subConn.close();
		}
		// this._threadRx.join();
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
		log.info(this + ".onStatusChanged: " + oldPhase + " -> " + newPhase);
	}

	@Override
	public DOMWrapperImplementation getDOMWrapperImplementation() {
		return this._domWrapperImpl;
	}

	@Override
	public int genDropTime() {
		int dt = this._current_drop_time;
		dt = this.setDropTime(dt * 2);
		// add a random value between 1 to 60 second
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int n = (rand.nextInt(60) + 1) * 1000;
		// log.trace("dropTime = base:" + dt + " + rand:" + n);
		return (dt + n);
	}

	@Override
	public int setDropTime(int time) {
		final int dt_max = 3600 * 1000;
		final int dt_min = 5 * 1000;
		if (time > dt_max)
			time = dt_max;
		if (time < dt_min)
			time = dt_min;
		this._current_drop_time = time;
		return time;
	}

}
