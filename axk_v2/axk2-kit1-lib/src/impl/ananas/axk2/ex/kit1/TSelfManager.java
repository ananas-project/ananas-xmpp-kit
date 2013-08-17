package impl.ananas.axk2.ex.kit1;

import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppCommandListener;
import ananas.axk2.core.XmppCommandStatus;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppStatus;
import ananas.axk2.core.api.IClient;
import ananas.axk2.core.api.ICommandRegistrar;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.core.command.StanzaCommandFactory;
import ananas.axk2.core.event.PhaseEvent;
import ananas.axk2.ex.kit1.contact.IContactModel;

public class TSelfManager extends TFilter {

	@Override
	public XmppEvent filter(XmppEvent event) {
		if (event instanceof PhaseEvent) {
			PhaseEvent pe = (PhaseEvent) event;
			if (XmppStatus.online.equals(pe.getNewPhase())) {
				this.__on_online(pe);
			}
		} else {
		}
		return event;
	}

	private void __on_online(PhaseEvent pe) {
		XmppConnection conn = pe.getConnection();
		ICommandRegistrar reg = (ICommandRegistrar) conn
				.getAPI(ICommandRegistrar.class);
		StanzaCommandFactory fact = (StanzaCommandFactory) reg
				.getFactory(StanzaCommandFactory.class);
		StanzaCommand cmd = fact.create(conn);
		StringBuilder sb = new StringBuilder();
		{
			// sb.append("<?xml version='1.0' encoding='UTF-8'?>");
			sb.append("<presence xmlns='jabber:client'>");
			sb.append("</presence>");
		}
		cmd.setString(sb.toString());
		cmd.setListener(new MyCmdListener());
		conn.send(cmd);

		// get my full jid
		IClient client = (IClient) conn.getAPI(IClient.class);
		XmppAddress addr = client.getBinding();
		IContactModel model = (IContactModel) conn.getAPI(IContactModel.class);
		model.getResource(addr.toString(), true);
	}

	class MyCmdListener implements XmppCommandListener {

		@Override
		public void onStatusChanged(XmppCommandStatus oldStatus,
				XmppCommandStatus newStatus) {
			// TODO Auto-generated method stub

		}
	}

}
