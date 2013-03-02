package ananas.lib.impl.axk.client.conn;

import org.apache.log4j.Logger;

import ananas.lib.axk.element.stream.Xmpp_stream;
import ananas.lib.util.log4j.AbstractLoggerFactory;

public abstract class XmppConnectionController implements
		IXmppConnectionController {

	private final static Logger logger = (new AbstractLoggerFactory() {
	}).getLogger();

	private final XmppConnection mConn;

	public XmppConnectionController(XmppConnection conn) {
		this.mConn = conn;
	}

	public XmppConnection getConnection() {
		return this.mConn;
	}

	public static final Object start_token = new Object();

	public Object getStartToken() {
		return start_token;
	}

	public void onReceive(Xmpp_stream stream, Object object) {
		logger.warn("unprocessed rx object : " + object);
	}

}
