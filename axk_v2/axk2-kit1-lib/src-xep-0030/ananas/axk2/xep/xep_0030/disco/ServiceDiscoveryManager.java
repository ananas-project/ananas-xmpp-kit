package ananas.axk2.xep.xep_0030.disco;

import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.api.IClient;
import ananas.axk2.core.api.ICommandAgent;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.xep.xep_0033.IServiceDiscoveryManager;

public class ServiceDiscoveryManager implements XmppFilter {

	private XmppConnection _conn;
	private final MySDM _sdm = new MySDM();

	@Override
	public void bind(XmppConnection connection) {
		_conn = connection;
	}

	@Override
	public void unbind(XmppConnection connection) {
		_conn = null;
	}

	@Override
	public XmppConnection getConnection() {
		return _conn;
	}

	@Override
	public XmppCommand filter(XmppCommand command) {
		return command;
	}

	@Override
	public XmppEvent filter(XmppEvent event) {
		return event;
	}

	class MySDM implements IServiceDiscoveryManager {

		int _id_gen = 1;

		@Override
		public void query(XmppAddress to) {
			XmppConnection conn = ServiceDiscoveryManager.this._conn;
			IClient client = (IClient) conn.getAPI(IClient.class);
			XmppAddress from = client.getBinding();
			String id = "disco-info-" + (this._id_gen++);
			StringBuilder sb = new StringBuilder();
			{
				sb.append("<iq xmlns='jabber:client'");
				sb.append(" type='get'");
				sb.append(" id='" + id + "'");
				sb.append(" from='" + from + "'");
				sb.append(" to='" + to + "'");
				sb.append(">");
				sb.append("<query xmlns='http://jabber.org/protocol/disco#info'/>");
				sb.append("</iq>");
			}
			ICommandAgent ca = (ICommandAgent) conn.getAPI(ICommandAgent.class);
			StanzaCommand cmd = ca.newStanzaCommand(conn);
			cmd.setString(sb.toString());
			conn.send(cmd);
		}
	}

	@Override
	public int listAPI(XmppAPIHandler h) {
		return XmppAPIHandler.Util.apiOfObject(_sdm, h);
	}

}
