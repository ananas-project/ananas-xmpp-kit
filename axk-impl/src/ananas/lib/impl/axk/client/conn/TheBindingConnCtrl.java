package ananas.lib.impl.axk.client.conn;

import ananas.lib.axk.element.stream.Xmpp_stream;
import ananas.lib.axk.element.xmpp_sasl.Xmpp_success;
import ananas.lib.axk.util.XmppStanzaBuilder;

public class TheBindingConnCtrl extends XmppConnectionController {

	public TheBindingConnCtrl(XmppConnection conn) {
		super(conn);
	}

	@Override
	public void onReceive(Xmpp_stream stream, Object object) {

		if (object.equals(this.getStartToken())) {
			this.doStartBind();

		} else if (object instanceof Xmpp_success) {
			// this.doBind(stream);
		} else {
			super.onReceive(stream, object);
		}
	}

	private void doStartBind() {

		// <iq type='set' id='bind_1'>
		// <bind xmlns='urn:ietf:params:xml:ns:xmpp-bind'/>
		// </iq>

		XmppStanzaBuilder xsb = XmppStanzaBuilder.Factory.newInstance();
		xsb.append("<iq type='set' id='bind_1'>");
		xsb.append("<bind xmlns='urn:ietf:params:xml:ns:xmpp-bind'>");
		xsb.append("<resource>");
		xsb.append(this.getConnection().getAccount().getResource());
		xsb.append("</resource>");
		xsb.append("</bind>");
		xsb.append("</iq>");

		byte[] ba = xsb.toByteArray();
		this.getConnection().syncSendBytes(ba, 0, ba.length);
	}

}
