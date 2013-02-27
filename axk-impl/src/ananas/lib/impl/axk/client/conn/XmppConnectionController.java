package ananas.lib.impl.axk.client.conn;

public abstract class XmppConnectionController implements
		IXmppConnectionController {

	private final XmppConnection mConn;

	public XmppConnectionController(XmppConnection conn) {
		this.mConn = conn;
	}

	public XmppConnection getConnection() {
		return this.mConn;
	}
}
