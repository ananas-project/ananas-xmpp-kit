package impl.ananas.axk2.ex.kit1;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppStatus;
import ananas.axk2.core.api.ICommandRegistrar;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.core.command.StanzaCommandFactory;
import ananas.axk2.core.event.PhaseEvent;

public class TPresenceManager extends TFilter {

	@Override
	public XmppEvent filter(XmppEvent event) {

		if (event instanceof PhaseEvent) {
			PhaseEvent pe = (PhaseEvent) event;
			if (XmppStatus.online.equals(pe.getNewPhase())) {
				XmppConnection conn = pe.getConnection();
				ICommandRegistrar reg = (ICommandRegistrar) conn
						.getAPI(ICommandRegistrar.class);
				StanzaCommandFactory fact = (StanzaCommandFactory) reg
						.getFactory(StanzaCommandFactory.class);
				StanzaCommand cmd = fact.create(conn);
				StringBuilder sb = new StringBuilder();
				{
					sb.append("<presence>");
					sb.append("</presence>");
				}
				cmd.setString(sb.toString());
				conn.send(cmd);
			}
		}

		return event;
	}

}
