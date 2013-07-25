package ananas.axk2.core;

import java.net.URI;

public class DefaultAddress implements XmppAddress {

	private final String _user;
	private final String _domain;
	private final String _resource;
	private String _string;

	public DefaultAddress(XmppAddress addr) {
		this._domain = addr.domain();
		this._resource = addr.resource();
		this._user = addr.user();
	}

	public DefaultAddress(String user, String domain, String res) {
		this._domain = domain;
		this._resource = res;
		this._user = user;
	}

	public DefaultAddress(String addr) {
		URI uri = this.stringToURI(addr);
		this._domain = uri.getHost();
		this._resource = this.pathToRes(uri.getPath());
		this._user = uri.getUserInfo();
	}

	private String pathToRes(String path) {
		if (path == null) {
			return null;
		} else {
			path = path.trim();
		}
		if (path.length() < 1) {
			return null;
		}
		// remove slash
		if (path.startsWith("/")) {
			return path.substring(1);
		} else {
			return path;
		}
	}

	private URI stringToURI(String addr) {
		return URI.create("jid://" + addr);
	}

	@Override
	public String user() {
		return this._user;
	}

	@Override
	public String domain() {
		return this._domain;
	}

	@Override
	public String resource() {
		return this._resource;
	}

	@Override
	public boolean isFull() {
		return (this._resource != null);
	}

	@Override
	public boolean isPure() {
		return (this._resource == null);
	}

	@Override
	public String toString() {
		String s = this._string;
		if (s == null) {
			if (_resource != null) {
				s = _user + "@" + _domain + "/" + _resource;
			} else {
				s = _user + "@" + _domain;
			}
			this._string = s;
		}
		return s;
	}

	public boolean equals(Object other) {
		if (other instanceof XmppAddress) {
			XmppAddress addr = (XmppAddress) other;
			String s2 = addr.toString();
			String s1 = this.toString();
			return s1.equals(s2);
		} else {
			return false;
		}
	}

	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public XmppAddress toFull() {
		if (this.isFull()) {
			return this;
		}
		String res = _resource;
		if (res == null) {
			res = "";
		}
		return new DefaultAddress(_user, _domain, res);
	}

	@Override
	public XmppAddress toPure() {
		if (this.isPure()) {
			return this;
		}
		return new DefaultAddress(_user, _domain, null);
	}
}
