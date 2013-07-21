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
