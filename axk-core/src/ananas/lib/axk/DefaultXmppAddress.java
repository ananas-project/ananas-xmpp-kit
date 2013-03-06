package ananas.lib.axk;

import java.net.URI;

public class DefaultXmppAddress implements XmppAddress {

	private final String mUser;
	private final String mHost;
	private final String mResource;

	public DefaultXmppAddress(String str) {
		URI uri = URI.create("xmpp://" + str);
		this.mUser = uri.getUserInfo();
		this.mHost = uri.getHost();
		String res = uri.getPath();
		if (res.length() > 0)
			this.mResource = res.substring(1);
		else
			this.mResource = null;
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
		return (this.mUser + "@" + this.mHost + "/" + this.mResource);
	}

	public String toStringPure() {
		return (this.mUser + "@" + this.mHost);
	}

	public String toString() {
		return this.toStringFull();
	}

	@Override
	public boolean isFull() {
		return (this.mResource != null);
	}

	@Override
	public boolean isPure() {
		return (this.mResource == null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mHost == null) ? 0 : mHost.hashCode());
		result = prime * result
				+ ((mResource == null) ? 0 : mResource.hashCode());
		result = prime * result + ((mUser == null) ? 0 : mUser.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultXmppAddress other = (DefaultXmppAddress) obj;
		if (mHost == null) {
			if (other.mHost != null)
				return false;
		} else if (!mHost.equals(other.mHost))
			return false;
		if (mResource == null) {
			if (other.mResource != null)
				return false;
		} else if (!mResource.equals(other.mResource))
			return false;
		if (mUser == null) {
			if (other.mUser != null)
				return false;
		} else if (!mUser.equals(other.mUser))
			return false;
		return true;
	}

}
