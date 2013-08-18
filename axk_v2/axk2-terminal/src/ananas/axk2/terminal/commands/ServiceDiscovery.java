package ananas.axk2.terminal.commands;

import java.io.PrintStream;

import ananas.axk2.core.DefaultAddress;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.terminal.Axk2TerminalCommand;
import ananas.axk2.xep.xep_0033.IServiceDiscoveryManager;
import ananas.blueprint4.terminal.ExecuteContext;
import ananas.blueprint4.terminal.Terminal;

public class ServiceDiscovery extends Axk2TerminalCommand {

	@Override
	public void execute(ExecuteContext context) {

		final Terminal terminal = context.getTerminal();
		final PrintStream out = terminal.getOutput();

		String jid = context.getParameters()[1];
		XmppAddress addr = new DefaultAddress(jid);
		out.println("find services of : " + jid);

		XmppConnection conn = this.getXmppConnection(context);
		IServiceDiscoveryManager sdm = (IServiceDiscoveryManager) conn
				.getAPI(IServiceDiscoveryManager.class);
		sdm.query(addr);

	}

}
