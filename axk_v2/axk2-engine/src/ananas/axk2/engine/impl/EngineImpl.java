package ananas.axk2.engine.impl;

import ananas.axk2.engine.XEngine;
import ananas.axk2.engine.XEngineContext;

class EngineImpl implements XEngine, XSuperConnection {

	private XThreadRuntime _curThreadRuntime;
	private boolean _isClose;
	private boolean _isOpen;
	private final XEngineContext _context;

	public EngineImpl(XEngineContext context) {
		this._context = context;
	}

	@Override
	public void start() {
		this.restart(true);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void restart(boolean newThread) {
		if (newThread) {
			XSuperConnection superConn = this;
			XThreadRuntime tr = new ThreadRuntimeImpl(superConn);
			this.__setCurrentThreadRuntime(tr);
		} else {
		}
	}

	private XThreadRuntime __setCurrentThreadRuntime(final XThreadRuntime newTR) {
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
	public void open() {
		this._isOpen = true;
	}

	@Override
	public void close() {
		this._isClose = true;
		this.__setCurrentThreadRuntime(null);
	}

	@Override
	public XEngineContext getContext() {
		return this._context;
	}

}
