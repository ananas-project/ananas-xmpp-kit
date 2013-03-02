package ananas.lib.axk.command;

import org.apache.log4j.Logger;

import ananas.lib.axk.XmppClient;
import ananas.lib.util.log4j.AbstractLoggerFactory;

public class TraceCommand extends ControlCommand {

	private final static Logger logger = (new AbstractLoggerFactory() {
	}).getLogger();

	@Override
	public void onSendByClient(XmppClient client) {
		logger.trace("    cross : " + client);
	}

}
