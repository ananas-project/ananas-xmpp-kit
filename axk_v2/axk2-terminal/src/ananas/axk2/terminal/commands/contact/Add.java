package ananas.axk2.terminal.commands.contact;

import java.io.PrintStream;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.ex.kit1.contact.IContactManager;
import ananas.axk2.terminal.Axk2TerminalCommand;
import ananas.blueprint4.terminal.ExecuteContext;
import ananas.blueprint4.terminal.Terminal;

public class Add extends Axk2TerminalCommand {

	@Override
	public void execute(ExecuteContext context) {

		final Terminal terminal = context.getTerminal();
		final PrintStream out = terminal.getOutput();

		String jid = context.getParameters()[2];

		out.println("add contact: " + jid);
		XmppConnection conn = this.getXmppConnection(context);
		IContactManager cm = (IContactManager) conn
				.getAPI(IContactManager.class);

		cm.getContact(jid, true);

	}

}
