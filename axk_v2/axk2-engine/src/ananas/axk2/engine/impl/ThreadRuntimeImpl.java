package ananas.axk2.engine.impl;

import ananas.axk2.core.XmppStatus;
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

	public ThreadRuntimeImpl(XSuperConnection parent) {
		this._parent = parent;
		this._threadRx = __newThread(new MyRxRunnable());
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
		for (int index = 0; !this._closed; index++) {
			final XSubConnection subConn = new SubConnectionImpl(this, index);
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

}
