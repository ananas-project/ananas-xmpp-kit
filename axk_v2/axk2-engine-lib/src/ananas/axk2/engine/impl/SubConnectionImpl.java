package ananas.axk2.engine.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import ananas.axk2.core.XmppAccount;
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

	private OutputStream _online_out;

	private final XmppAccount _final_account;

	public SubConnectionImpl(int index, XThreadRuntime parent, int dropTime) {
		this._parent = parent;
		this._dropTime = dropTime;
		this._stanzaProcMan = new StanzaProcessorManagerImpl();
		this._saslProcMan = new SASLProcessorManagerImpl();
		this._final_account = parent.getNextAccount();
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
		this.__run();
		log.trace(this + ".run(end)");
	}

	private void __run() {

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

		int dtBase = this._dropTime;
		XmppStatus phase = this.getParent().getPhase();
		if (phase.equals(XmppStatus.error)) {
			return;
		} else if (phase.equals(XmppStatus.online)) {
			dtBase = 1000;
			this.getParent().setDropTime(dtBase);
		}

		// dropped phase
		if (this._dropTime > 0) {
			parent.setPhase(XmppStatus.dropped);
			final int rand = this.__random(1000, 60 * 1000);
			log.info("retry after " + (dtBase / 1000) + "+" + (rand / 1000)
					+ " sec");
			int timeout = dtBase + rand;
			for (; timeout > 0;) {
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
		// return
	}

	private int __random(int a, int b) {
		int min = Math.min(a, b);
		int max = Math.max(a, b);
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int n = rand.nextInt(max - min);
		return (n + min);
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

	public String toString() {
		if (this._isOpen) {
		}
		return super.toString();
	}

	@Override
	public OutputStream getOnlineOutput() {
		return this._online_out;
	}

	@Override
	public void setOnlineOutput(OutputStream out) {
		this._online_out = out;
	}

	@Override
	public XmppAccount getFinalAccount() {
		return this._final_account;
	}
}
