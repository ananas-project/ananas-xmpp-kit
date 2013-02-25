package ananas.lib.impl.axk.client.target;

import ananas.lib.axk.DefaultXmppAccount;
import ananas.lib.axk.XmppAccount;
import ananas.lib.axk.XmppClientExAPI;
import ananas.lib.axk.XmppEnvironment;
import ananas.lib.axk.XmppPhase;
import ananas.lib.axk.api.IExConnection;
import ananas.lib.axk.api.IExCore;
import ananas.lib.axk.api.IExShell;

public class Tar_shell extends Tar_abstractClient implements IExShell {

	@Override
	public XmppClientExAPI getExAPI(Class<? extends XmppClientExAPI> apic) {

		if (apic == null) {
			return null;

		} else if (apic.equals(IExShell.class)) {
			IExShell api = this;
			return api;

		} else if (apic.equals(IExCore.class)) {
			IExCore api = this;
			return api;

		} else if (apic.equals(IExConnection.class)) {
			IExConnection api = this;
			return api;

		} else {
			return super.getExAPI(apic);
		}
	}

	private IExConnection mTargetConn;
	private IExCore mTargetCore;
	private DefaultXmppAccount mAccountCache;

	private IExConnection _getTargetConnection() {
		IExConnection api = this.mTargetConn;
		if (api == null) {
			api = (IExConnection) super.getExAPI(IExConnection.class);
			this.mTargetConn = api;
		}
		return api;
	}

	private IExCore _getTargetCore() {
		IExCore api = this.mTargetCore;
		if (api == null) {
			api = (IExCore) super.getExAPI(IExCore.class);
			this.mTargetCore = api;
		}
		return api;
	}

	@Override
	public XmppEnvironment getEnvironment() {
		IExCore core = this._getTargetCore();
		return core.getEnvironment();
	}

	@Override
	public XmppAccount getAccount() {
		DefaultXmppAccount acc = this.mAccountCache;
		if (acc == null) {
			IExCore core = this._getTargetCore();
			acc = new DefaultXmppAccount(core.getAccount());
			acc.password = "******";
			this.mAccountCache = acc;
		}
		return acc;
	}

	@Override
	public void setStatus(XmppPhase phase) {
		IExConnection conn = this._getTargetConnection();
		conn.setStatus(phase);
	}

	@Override
	public XmppPhase getStatus() {
		IExConnection conn = this._getTargetConnection();
		return conn.getStatus();
	}

	@Override
	public XmppPhase getPhase() {
		IExConnection conn = this._getTargetConnection();
		return conn.getPhase();
	}

	@Override
	public void close() {
		IExConnection conn = this._getTargetConnection();
		conn.close();
	}

	@Override
	public void connect() {
		IExConnection conn = this._getTargetConnection();
		conn.connect();
	}

	@Override
	public void disconnect() {
		IExConnection conn = this._getTargetConnection();
		conn.disconnect();
	}

}
