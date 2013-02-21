package ananas.lib.axk.command;

import ananas.lib.axk.XmppClient;

public class TraceCommand extends ControlCommand {

	@Override
	public void onSendByClient(XmppClient client) {
		System.out.println("    cross : " + client);
	}

}
