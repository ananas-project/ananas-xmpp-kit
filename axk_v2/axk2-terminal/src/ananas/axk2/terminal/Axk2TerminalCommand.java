package ananas.axk2.terminal;

import java.util.Map;

import ananas.axk2.core.XmppConnection;
import ananas.blueprint4.terminal.Command;
import ananas.blueprint4.terminal.ExecuteContext;

public abstract class Axk2TerminalCommand implements Command {

	public XmppConnection getXmppConnection(ExecuteContext context) {
		Map<String, Object> global = context.getTerminal().getGlobal();
		XmppConnection conn = (XmppConnection) global.get(XmppConnection.class
				.getName());
		return conn;
	}

}
