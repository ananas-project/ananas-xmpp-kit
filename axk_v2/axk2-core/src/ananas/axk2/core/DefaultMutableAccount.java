package ananas.axk2.core;

public class DefaultMutableAccount implements XmppAccount {

	public int _port;
	public String _host;
	public String _password;
	public boolean _ignoreTLSError;
	public boolean _useSSL;
	public XmppAddress _jid;
	public String _resource;

	public DefaultMutableAccount() {
		this._host = "";
		this._port = 5222;
		this._password = "";
		this._jid = null;
		this._ignoreTLSError = false;
		this._useSSL = false;
		this._resource = "";
	}

	public DefaultMutableAccount(XmppAccount init) {
		this._host = init.host();
		this._port = init.port();
		this._password = init.password();
		this._jid = new DefaultAddress(init.jid());
		this._ignoreTLSError = init.ignoreTLSError();
		this._useSSL = init.useSSL();
		this._resource = init.resource();
	}

	@Override
	public XmppAddress jid() {
		return this._jid;
	}

	@Override
	public String password() {
		return this._password;
	}

	@Override
	public String host() {
		return this._host;
	}

	@Override
	public int port() {
		return this._port;
	}

	@Override
	public boolean useSSL() {
		return this._useSSL;
	}

	@Override
	public boolean ignoreTLSError() {
		return this._ignoreTLSError;
	}

	@Override
	public String resource() {
		return this._resource;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[" + this.getClass().getSimpleName());
		sb.append(" jid:" + this._jid);
		sb.append(" password:" + this.__pswToString());
		sb.append(" host:" + this._host);
		sb.append(" port:" + this._port);
		sb.append(" resource:" + this._resource);
		sb.append(" use_ssl:" + this._useSSL);
		sb.append(" ignore_ssl_error:" + this._ignoreTLSError);
		sb.append("]");
		return sb.toString();
	}

	private String __pswToString() {
		final String psw = this._password;
		if (psw == null) {
			return ("" + psw);
		}
		char[] chs = psw.toCharArray();
		for (int i = chs.length - 1; i >= 0; i--) {
			chs[i] = '*';
		}
		return new String(chs);
	}

}
