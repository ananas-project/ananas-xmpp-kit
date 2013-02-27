package ananas.lib.impl.axk.client.conn;

import java.io.UnsupportedEncodingException;

import ananas.lib.axk.XmppAccount;
import ananas.lib.axk.XmppAddress;
import ananas.lib.axk.element.stream.Xmpp_features;
import ananas.lib.axk.element.stream.Xmpp_stream;
import ananas.lib.axk.element.xmpp_sasl.Xmpp_mechanisms;
import ananas.lib.axk.util.Base64;
import ananas.lib.axk.util.XmppStanzaBuilder;

public class ThePlainSaslConnCtrl extends XmppConnectionController {

	public static class Factory implements IXmppConnectionControllerFactory {

		@Override
		public IXmppConnectionController newController(XmppConnection conn) {
			return new ThePlainSaslConnCtrl(conn);
		}

	}

	public ThePlainSaslConnCtrl(XmppConnection conn) {
		super(conn);
	}

	@Override
	public void onReceive(Xmpp_stream stream, Object object) {

		if (object instanceof Xmpp_features) {
			Xmpp_features features = (Xmpp_features) object;

			Xmpp_mechanisms mechanisms = (Xmpp_mechanisms) features
					.findItemByClass(Xmpp_mechanisms.class);
			if (mechanisms != null) {
				this.doAuth();
				return;
			}

		}

		System.err.println(this + "::unprocessed rx object : " + object);

	}

	public void doAuth() {

		// <auth xmlns='urn:ietf:params:xml:ns:xmpp-sasl'
		// mechanism='PLAIN'>AGh4dwAx</auth>

		String base64string = null;

		try {

			XmppAccount account = this.getConnection().getAccount();
			// System.err.println("sasl plain with : " + account);
			XmppAddress addr = account.getAddress();

			StringBuilder sb = new StringBuilder();
			sb.append(addr.toStringPure());
			sb.append("\0");
			sb.append(addr.getUser());
			sb.append("\0");
			sb.append(account.getPassword());
			byte[] ba1 = sb.toString().getBytes("UTF-8");

			base64string = this.bytesToBase64(ba1);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		XmppStanzaBuilder xsb = XmppStanzaBuilder.Factory.newInstance();
		xsb.append("<auth");
		xsb.appendAttribute("xmlns", "urn:ietf:params:xml:ns:xmpp-sasl");
		xsb.appendAttribute("mechanism", "PLAIN");
		xsb.append(">");
		xsb.append(base64string);
		xsb.append("</auth>");

		byte[] ba = xsb.toByteArray();
		this.getConnection().syncSendBytes(ba, 0, ba.length);

	}

	private String bytesToBase64(byte[] buff) {
		return Base64.getInstance().doEncode(buff);
	}

}
