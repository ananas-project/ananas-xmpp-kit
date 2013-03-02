package ananas.lib.impl.axk.client.conn;

import org.apache.log4j.Logger;

import ananas.lib.axk.element.stream.Xmpp_stream;
import ananas.lib.util.log4j.AbstractLoggerFactory;

public class TheOnlineConnCtrl extends XmppConnectionController {

	private final static Logger logger = (new AbstractLoggerFactory() {
	}).getLogger();

	public TheOnlineConnCtrl(XmppConnection conn) {
		super(conn);
	}

	@Override
	public void onReceive(Xmpp_stream stream, Object object) {
		logger.info("onReceive:" + object);
	}

}
