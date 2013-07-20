package ananas.axk2.engine.impl;

import java.io.IOException;
import java.io.OutputStream;

import ananas.axk2.core.XmppStatus;
import ananas.axk2.engine.api.XEncoding;
import ananas.axk2.engine.api.XEngineCore;
import ananas.axk2.engine.api.XEngineRuntimeContext;
import ananas.axk2.engine.api.XStanzaProcessorManager;
import ananas.axk2.engine.api.XSubConnection;
import ananas.axk2.engine.api.XThreadRuntime;
import ananas.lib.util.logging.Logger;

class SubConnectionImpl implements XSubConnection {

	final static Logger log = Logger.Agent.getLogger();

	private final XThreadRuntime _parent;
	private final int _dropTime;
	private boolean _isClose;
	private boolean _isOpen;
	private boolean _hasOnline;

	private final XStanzaProcessorManager _stanzaProcMan;
	private final SASLProcessorManager _saslProcMan;

	private SocketAgent _curSockAgent;

	public SubConnectionImpl(int index, XThreadRuntime parent, int dropTime) {
		this._parent = parent;
		this._dropTime = dropTime;
		this._stanzaProcMan = new StanzaProcessorManagerImpl();
		this._saslProcMan = new SASLProcessorManagerImpl();
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
		final XThreadRuntime parent = this.getParent();
		parent.setPhase(XmppStatus.connect);
		try {
			XSubConnection subConn = this;
			XEngineRuntimeContext erc = new ErcRoot(subConn);
			XEngineCore core = XEngineCore.Factory.newInstance();
			core.run(erc);
		} catch (Exception e) {
			log.error(e);
		}

		// dropped phase
		if (this._dropTime > 0) {
			parent.setPhase(XmppStatus.dropped);
			for (int timeout = _dropTime; timeout > 0;) {
				if (this._isClose) {
					break;
				} else {

					log.info("retry after " + (timeout / 1000) + " sec");

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

	@Override
	public XStanzaProcessorManager getStanzaProcessorManager() {
		return this._stanzaProcMan;
	}

	@Override
	public void send_sync(String data) throws IOException {
		OutputStream out = this._curSockAgent.getOutputStream();
		out.write(data.getBytes(XEncoding.theDefault));
	}

	@Override
	public void setCurrentSocketAgent(SocketAgent sa) {
		if (sa != null) {
			this._curSockAgent = sa;
		}
	}

	@Override
	public SocketAgent getCurrentSocketAgent() {
		return this._curSockAgent;
	}

	@Override
	public SASLProcessorManager getSASLProcessorManager() {
		return this._saslProcMan;
	}
}
