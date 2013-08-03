package ananas.axk2.terminal.commands;

import java.io.PrintStream;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.api.IClient;
import ananas.axk2.terminal.Axk2TerminalCommand;
import ananas.blueprint4.terminal.ExecuteContext;
import ananas.blueprint4.terminal.Terminal;

public class Null extends Axk2TerminalCommand {

	@Override
	public void execute(ExecuteContext context) {

		final Terminal terminal = context.getTerminal();
		final PrintStream out = terminal.getOutput();
		out.println("do null ...");

		XmppConnection conn = this.getXmppConnection(context);
		IClient client = (IClient) conn.getAPI(IClient.class);
		client.toString();// do something

	}

}
