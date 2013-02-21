package ananas.lib.impl.axk;

import ananas.lib.axk.XmppAccount;
import ananas.lib.axk.XmppClient;
import ananas.lib.axk.XmppClientExAPI;
import ananas.lib.axk.XmppCommand;
import ananas.lib.axk.XmppEnvironment;
import ananas.lib.axk.XmppEventListener;
import ananas.lib.axk.api.IExCore;

public class TheXmppClientCore implements XmppClient, IExCore {

	private final XmppEnvironment mEnvi;
	private final XmppAccount mAccount;

	public TheXmppClientCore(XmppEnvironment envi, XmppAccount account) {
		this.mEnvi = envi;
		this.mAccount = account;
	}

	@Override
	public void dispatch(XmppCommand cmd) {
		cmd.onSendByClient(this);
	}

	@Override
	public XmppEnvironment getEnvironment() {
		return this.mEnvi;
	}

	@Override
	public void setXmppEventListener(XmppEventListener listener) {
	}

	@Override
	public XmppAccount getAccount() {
		return this.mAccount;
	}

	@Override
	public XmppClientExAPI getExAPI(Class<? extends XmppClientExAPI> apiClass) {
		if (apiClass.equals(IExCore.class)) {
			IExCore api = this;
			return api;
		} else {
			return null;
		}
	}

}
