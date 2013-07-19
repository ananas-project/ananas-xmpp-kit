package ananas.axk2.engine.impl;

import ananas.axk2.core.XmppStatus;

public class XStanzaProcessorManagerImpl implements XStanzaProcessorManager {

	private XStanzaProcessor _current;
	private final XStanzaProcessor _proc_login;
	private final XStanzaProcessor _proc_online;

	public XStanzaProcessorManagerImpl() {

		this._proc_login = new XStanzaProcessorForLogin();
		this._proc_online = new XStanzaProcessorForOnline();

		XStanzaProcessor proc = this.getProcessorForStatus(XmppStatus.connect);
		this.setCurrentProcessor(proc);
	}

	@Override
	public XStanzaProcessor getCurrentProcessor() {
		return this._current;
	}

	@Override
	public void setCurrentProcessor(XStanzaProcessor proc) {
		if (proc != null) {
			this._current = proc;
		}
	}

	@Override
	public XStanzaProcessor getProcessorForStatus(XmppStatus status) {
		if (status == null) {
		} else if (status.equals(XmppStatus.online)) {
			return this._proc_online;
		} else {
		}
		return this._proc_login;
	}

}
