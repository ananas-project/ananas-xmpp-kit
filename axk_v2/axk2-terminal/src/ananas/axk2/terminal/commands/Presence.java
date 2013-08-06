package ananas.axk2.terminal.commands;

import java.io.PrintStream;

import ananas.axk2.core.XmppCommandListener;
import ananas.axk2.core.XmppCommandStatus;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.api.ICommandRegistrar;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.core.command.StanzaCommandFactory;
import ananas.axk2.terminal.Axk2TerminalCommand;
import ananas.blueprint4.terminal.ExecuteContext;
import ananas.blueprint4.terminal.Terminal;

public class Presence extends Axk2TerminalCommand {

	@Override
	public void execute(ExecuteContext context) {

		final Terminal terminal = context.getTerminal();
		final PrintStream out = terminal.getOutput();
		out.println("send presence ...");

		XmppConnection conn = this.getXmppConnection(context);
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
		cmd.setListener(new MyCmdListener());
		conn.send(cmd);

	}

	class MyCmdListener implements XmppCommandListener {

		@Override
		public void onStatusChanged(XmppCommandStatus oldStatus,
				XmppCommandStatus newStatus) {

			System.out.println(this + ".onStatusChanged:" + newStatus);

		}
	}

}
