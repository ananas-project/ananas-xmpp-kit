package ananas.axk2.terminal.commands.contact;

import java.io.PrintStream;
import java.util.List;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.ex.kit1.contact.IContactModel;
import ananas.axk2.ex.kit1.contact.XmppResource;
import ananas.axk2.terminal.Axk2TerminalCommand;
import ananas.blueprint4.terminal.ExecuteContext;
import ananas.blueprint4.terminal.Terminal;

public class Resource extends Axk2TerminalCommand {

	@Override
	public void execute(ExecuteContext context) {

		final Terminal terminal = context.getTerminal();
		final PrintStream out = terminal.getOutput();

		out.println("list all resources:");
		XmppConnection conn = this.getXmppConnection(context);
		IContactModel cm = (IContactModel) conn
				.getAPI(IContactModel.class);
		List<XmppResource> list = cm.listResources();
		for (XmppResource res : list) {
			out.println("    " + res);
		}

	}

}
