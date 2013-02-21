package ananas.lib.axk;

public class DefaultXmppAccount implements XmppAccount {

	public int port = 5222;
	public String host;
	public String password;
	public XmppAddress address;
	public boolean useSSL = false;
	public boolean ignoreSSLError = false;
	public String resource = "axk-client";

	@Override
	public String host() {
		return this.host;
	}

	@Override
	public int port() {
		return this.port;
	}

	@Override
	public String password() {
		return this.password;
	}

	@Override
	public XmppAddress address() {
		return this.address;
	}

	@Override
	public String resource() {
		return this.resource;
	}

	@Override
	public boolean useSSL() {
		return this.useSSL;
	}

	@Override
	public boolean ignoreSSLError() {
		return this.ignoreSSLError;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(this.getClass().getName());
		sb.append(" jid=" + this.address);
		sb.append(" password=" + this.password);
		sb.append(" host=" + this.host);
		sb.append(" port=" + this.port);
		sb.append(" resource=" + this.resource);
		sb.append(" useSSL=" + this.useSSL);
		sb.append(" ignoreSSLError=" + this.ignoreSSLError);
		sb.append("]");
		return sb.toString();
	}

}
