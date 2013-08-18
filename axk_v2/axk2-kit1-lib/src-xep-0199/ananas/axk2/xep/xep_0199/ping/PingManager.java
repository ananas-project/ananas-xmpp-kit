package ananas.axk2.xep.xep_0199.ping;

import org.w3c.dom.NodeList;

import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.api.ICommandAgent;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.core.event.StanzaEvent;

public class PingManager implements XmppFilter {

	private XmppConnection _conn;

	@Override
	public int findAPI(Class<?> apiClass, XmppAPIHandler h) {
		return XmppFilter.find_continue;
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
			StanzaEvent se = ((StanzaEvent) event);

			// String str = se.getString();
			// System.out.println(this + ":event:" + str);

			XmppAddress from = se.get_from();
			XmppAddress to = se.get_to();
			String id = se.get_id();
			String type = se.get_type();

			String namespaceURI = "urn:xmpp:ping";
			String localName = "ping";
			NodeList rlt = se.getElement().getElementsByTagNameNS(namespaceURI,
					localName);
			if (rlt.getLength() > 0)
				if (type.equals("get")) {
					this.doGet(se, from, to, id, type);
				}

		}
		return event;
	}

	private void doGet(StanzaEvent se, XmppAddress from, XmppAddress to,
			String id, String type) {

		StringBuilder sb = new StringBuilder();
		{
			sb.append("<iq xmlns='jabber:client'");
			sb.append(" from='" + to + "'");
			sb.append(" to='" + from + "'");
			sb.append(" id='" + id + "'");
			sb.append(" type='result'");
			sb.append("/>");
		}
		XmppConnection conn = se.getConnection();
		ICommandAgent ca = (ICommandAgent) conn.getAPI(ICommandAgent.class);
		StanzaCommand cmd = ca.newStanzaCommand(conn);
		cmd.setString(sb.toString());
		conn.send(cmd);
	}

}
