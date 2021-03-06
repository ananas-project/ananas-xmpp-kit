package ananas.axk2.terminal.commands;

import java.io.PrintStream;
import java.util.List;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.ex.kit1.contact.IContactModel;
import ananas.axk2.ex.kit1.contact.XmppContact;
import ananas.axk2.terminal.Axk2TerminalCommand;
import ananas.blueprint4.terminal.ExecuteContext;
import ananas.blueprint4.terminal.Terminal;

public class Contact extends Axk2TerminalCommand {

	@Override
	public void execute(ExecuteContext context) {
		final Terminal terminal = context.getTerminal();
		final PrintStream out = terminal.getOutput();

		out.println("list all contacts:");
		XmppConnection conn = this.getXmppConnection(context);
		IContactModel cm = (IContactModel) conn
				.getAPI(IContactModel.class);
		List<XmppContact> list = cm.listContacts();
		for (XmppContact contact : list) {
			out.println("    " + contact);
		}
	}

}
