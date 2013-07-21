package impl.ananas.axk2.core.base;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;

public class Filter implements XmppFilter, XmppAPI {

	private XmppConnection _conn;

	@Override
	public XmppAPI getAPI(Class<?> apiClass) {
		return this.getAPI(apiClass, null);
	}

	@Override
	public XmppAPI getAPI(Class<?> apiClass, Object after) {
		if (apiClass.isInstance(this)) {
			return this;
		} else {
			return null;
		}
	}

	@Override
	public void bind(XmppConnection connection) {
		this._conn = connection;
	}

	@Override
	public void unbind(XmppConnection connection) {
		if (connection.equals(this._conn)) {
			this._conn = null;
		}
	}

	@Override
	public XmppConnection getConnection() {
		return this._conn;
	}

	@Override
	public XmppCommand filter(XmppCommand command) {
		return command;
	}

	@Override
	public XmppEvent filter(XmppEvent event) {
		return event;
	}

}
