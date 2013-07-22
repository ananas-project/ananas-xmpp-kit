package ananas.axk2.engine.impl;

import ananas.axk2.core.XmppStatus;
import ananas.axk2.engine.XEngine;
import ananas.axk2.engine.XEngineContext;
import ananas.axk2.engine.api.XSuperConnection;
import ananas.axk2.engine.api.XThreadRuntime;

class EngineImpl implements XEngine, XSuperConnection {

	private XThreadRuntime _curThreadRuntime;
	// private boolean _isClose;
	// private boolean _isOpen;
	private final XEngineContext _context;
	private XmppStatus _status = XmppStatus.initial;

	public EngineImpl(XEngineContext context) {
		this._context = context;
	}

	@Override
	public void connect() {
		this._status = XmppStatus.online;
		XSuperConnection superConn = this;
		XThreadRuntime tr = new ThreadRuntimeImpl(superConn);
		this.__setCurrentThreadRuntime(tr);
	}

	@Override
	public void disconnect() {
		this._status = XmppStatus.offline;
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
		return this._status;
	}

	@Override
	public XmppStatus getPhase() {
		XThreadRuntime tr = this.getCurrentTR();
		if (tr == null) {
			return XmppStatus.offline;
		}
		return tr.getPhase();
	}

	@Override
	public void send() {
		// TODO Auto-generated method stub

	}

	@Override
	public XEngine getEngine() {
		return this;
	}

}
