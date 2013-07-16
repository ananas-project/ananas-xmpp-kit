package ananas.axk2.core;

public class MutableAccount implements XmppAccount {

	public int _port;
	public String _host;
	public String _password;
	public String _resource;
	public boolean _ignoreTLSError;
	public boolean _useSSL;
	public XmppAddress _jid;

	public MutableAccount() {
	}

	public MutableAccount(XmppAccount init) {
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

}
