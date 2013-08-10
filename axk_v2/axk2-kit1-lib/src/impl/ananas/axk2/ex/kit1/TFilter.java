package impl.ananas.axk2.ex.kit1;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;

public class TFilter implements XmppFilter, XmppAPI {

	private XmppConnection _conn;

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

	@Override
	public int findAPI(Class<?> apiClass, XmppAPIHandler h) {
		XmppAPI api = this.getAPI(apiClass);
		if (api == null) {
			return XmppAPI.find_continue;
		} else {
			return h.onAPI(apiClass, api);
		}
	}

	@Override
	public XmppAPI getAPI(Class<?> apiClass) {
		if (apiClass.isInstance(this)) {
			return this;
		} else {
			return null;
		}
	}

}
