package ananas.axk2.core;

public class DefaultAccount implements XmppAccount {

	private final int _port;
	private final String _host;
	private final String _password;
	private final boolean _ignoreTLSError;
	private final boolean _useSSL;
	private final XmppAddress _jid;
	private final String _resource;

	public DefaultAccount(XmppAccount init) {
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
