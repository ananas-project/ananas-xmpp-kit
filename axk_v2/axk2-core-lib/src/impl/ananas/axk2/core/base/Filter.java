package impl.ananas.axk2.core.base;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;

public class Filter implements XmppFilter, XmppAPI {

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
	public int listAPI(XmppAPIHandler h) {
		return XmppAPIHandler.Util.apiOfObject(this, h);
	}

}
