package ananas.axk2.core.bp.target;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.event.StanzaEvent;

public class DefaultNSFilter implements XmppFilter {

	private XmppConnection _conn;

	@Override
	public int findAPI(Class<?> apiClass, XmppAPIHandler h) {
		return XmppAPI.find_continue;
	}

	@Override
	public void bind(XmppConnection connection) {
		this._conn = connection;
	}

	@Override
	public void unbind(XmppConnection connection) {
		this._conn = null;
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

		if (event instanceof StanzaEvent) {
		} else {
		}

		return event;
	}

}
