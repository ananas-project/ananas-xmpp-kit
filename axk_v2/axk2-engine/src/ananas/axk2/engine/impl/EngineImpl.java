package ananas.axk2.engine.impl;

import ananas.axk2.core.XmppStatus;
import ananas.axk2.engine.XEngine;
import ananas.axk2.engine.XEngineContext;

class EngineImpl implements XEngine, XSuperConnection {

	private XThreadRuntime _curThreadRuntime;
	// private boolean _isClose;
	// private boolean _isOpen;
	private final XEngineContext _context;

	public EngineImpl(XEngineContext context) {
		this._context = context;
	}

	@Override
	public void connect() {
		XSuperConnection superConn = this;
		XThreadRuntime tr = new ThreadRuntimeImpl(superConn);
		this.__setCurrentThreadRuntime(tr);
	}

	@Override
	public void disconnect() {
		this.__setCurrentThreadRuntime(null);
	}

	@Override
	public void open() {
		// this._isOpen = true;
	}

	@Override
	public void close() {
		// this._isClose = true;
		// this.stop();
		this.__setCurrentThreadRuntime(null);
	}

	private XThreadRuntime __setCurrentThreadRuntime(XThreadRuntime newTR) {

		final XThreadRuntime oldTR;
		synchronized (this) {
			oldTR = this._curThreadRuntime;
			this._curThreadRuntime = newTR;
		}
		if (oldTR != null) {
			oldTR.close();
		}
		if (newTR != null) {
			newTR.open();
		}
		return oldTR;
	}

	@Override
	public XEngineContext getContext() {
		return this._context;
	}

	@Override
	public XThreadRuntime getCurrentTR() {
		return this._curThreadRuntime;
	}

	@Override
	public XmppStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppStatus getPhase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void send() {
		// TODO Auto-generated method stub

	}

}
