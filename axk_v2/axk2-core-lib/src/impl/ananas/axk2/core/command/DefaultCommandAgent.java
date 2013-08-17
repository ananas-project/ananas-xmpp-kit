package impl.ananas.axk2.core.command;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.api.ICommandAgent;
import ananas.axk2.core.api.ICommandRegistrar;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.core.command.StanzaCommandFactory;

public class DefaultCommandAgent implements ICommandAgent {

	@Override
	public StanzaCommand newStanzaCommand(XmppConnection conn) {

		ICommandRegistrar reg = (ICommandRegistrar) conn
				.getAPI(ICommandRegistrar.class);
		StanzaCommandFactory fact = (StanzaCommandFactory) reg
				.getFactory(StanzaCommandFactory.class);
		StanzaCommand cmd = fact.create(conn);

		return cmd;
	}

}
