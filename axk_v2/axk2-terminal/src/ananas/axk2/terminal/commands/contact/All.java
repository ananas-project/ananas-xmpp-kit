package ananas.axk2.terminal.commands.contact;

import ananas.axk2.terminal.Axk2TerminalCommand;
import ananas.blueprint4.terminal.ExecuteContext;

public class All extends Axk2TerminalCommand {

	@Override
	public void execute(ExecuteContext context) {

		context.getTerminal().execute("contact ");
		context.getTerminal().execute("contact group");
		context.getTerminal().execute("contact resource");
	}

}
