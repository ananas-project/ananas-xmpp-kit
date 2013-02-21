package ananas.lib.axk;

import java.net.URI;

public class XmppAddress {

	private final String mUser;
	private final String mHost;
	private final String mResource;

	public XmppAddress(String str) {
		URI uri = URI.create("xmpp://" + str);
		this.mUser = uri.getUserInfo();
		this.mHost = uri.getHost();
		this.mResource = uri.getPath();
	}

	public String getUser() {
		return this.mUser;
	}

	public String getHost() {
		return this.mHost;
	}

	public String getResource() {
		return this.mResource;
	}

	public String toStringFull() {
		return (this.mUser + "@" + this.mHost + this.mResource);
	}

	public String toStringPure() {
		return (this.mUser + "@" + this.mHost);
	}

	public String toString() {
		return this.toStringFull();
	}

}
