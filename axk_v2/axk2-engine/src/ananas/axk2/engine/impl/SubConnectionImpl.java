package ananas.axk2.engine.impl;

import ananas.axk2.core.XmppStatus;
import ananas.lib.util.logging.Logger;

class SubConnectionImpl implements XSubConnection {

	final static Logger log = Logger.Agent.getLogger();

	private final XThreadRuntime _parent;
	private final int _dropTime;

	private boolean _isClose;

	private boolean _isOpen;

	private boolean _hasOnline;

	public SubConnectionImpl(XThreadRuntime parent, int dropTime) {
		this._parent = parent;
		this._dropTime = dropTime;
	}

	public void open() {
		log.trace(this + ".open()");
		this._isOpen = true;
	}

	public void close() {
		log.trace(this + ".close()");
		this._isClose = true;
	}

	@Override
	public XThreadRuntime getParent() {
		return this._parent;
	}

	@Override
	public void run() {
		log.trace(this + ".run(begin)");

		// log to online

		// dropped phase
		XThreadRuntime parent = this.getParent();
		parent.setPhase(XmppStatus.dropped);
		if (this._dropTime > 0) {
			parent.setPhase(XmppStatus.dropped);
			for (int timeout = _dropTime; timeout > 0;) {
				if (this._isClose) {
					break;
				} else {
					int step = this.__getDropTimeStep(timeout);
					timeout -= step;
					try {
						Thread.sleep(step);
					} catch (InterruptedException e) {
						log.error(e);
					}
				}
			}
		}
		log.trace(this + ".run(end)");
	}

	private int __getDropTimeStep(int time) {
		int min = 1000 * 60;
		if (time < (2 * min)) {
			return 1000;
		} else if (time < (10 * min)) {
			return (10 * 1000);
		} else {
			return (60 * 1000);
		}
	}

	@Override
	public boolean hasOnline() {
		return this._hasOnline;
	}
}
