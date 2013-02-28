package ananas.lib.impl.axk.client.conn;

import ananas.lib.axk.element.stream.Xmpp_stream;

public abstract class XmppConnectionController implements
		IXmppConnectionController {

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
		System.err.println(this + "::unprocessed rx object : " + object);
	}

}
