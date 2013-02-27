package ananas.lib.impl.axk.client.conn;

import ananas.lib.axk.element.stream.Xmpp_stream;
import ananas.lib.axk.util.XmppStanzaBuilder;

public class TheInitConnCtrl extends XmppConnectionController {

	public TheInitConnCtrl(XmppConnection conn) {
		super(conn);
	}

	@Override
	public void onReceive(Xmpp_stream stream, Object object) {

		this.doStartTLS();

	}

	private void doStartTLS() {
		XmppStanzaBuilder xsb = XmppStanzaBuilder.Factory.newInstance();
		xsb.append("<starttls");
		xsb.appendAttribute("xmlns", "urn:ietf:params:xml:ns:xmpp-tls");
		xsb.append("/>");
		byte[] ba = xsb.toByteArray();
		this.getConnection().syncSendBytes(ba, 0, ba.length);
	}

}
