package ananas.lib.axk.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ananas.lib.axk.XmppClient;

public class TraceCommand extends ControlCommand {

	final static Logger logger = LogManager.getLogger(new Object() {
	});

	@Override
	public void onSendByClient(XmppClient client) {
		logger.trace("    cross : " + client);
	}

}
