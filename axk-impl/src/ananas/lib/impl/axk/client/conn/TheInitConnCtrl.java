package ananas.lib.impl.axk.client.conn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ananas.lib.axk.element.stream.Xmpp_features;
import ananas.lib.axk.element.stream.Xmpp_stream;
import ananas.lib.axk.element.xmpp_sasl.Xmpp_mechanism;
import ananas.lib.axk.element.xmpp_sasl.Xmpp_mechanisms;
import ananas.lib.axk.element.xmpp_tls.Xmpp_proceed;
import ananas.lib.axk.element.xmpp_tls.Xmpp_starttls;
import ananas.lib.axk.util.XmppStanzaBuilder;

public class TheInitConnCtrl extends XmppConnectionController {

	private Map<String, IXmppConnectionControllerFactory> mCtrlFactoryMap;

	public TheInitConnCtrl(XmppConnection conn) {
		super(conn);
	}

	private Map<String, IXmppConnectionControllerFactory> getCtrlFactoryMap() {
		Map<String, IXmppConnectionControllerFactory> map = this.mCtrlFactoryMap;
		if (map == null) {
			map = new HashMap<String, IXmppConnectionControllerFactory>();

			map.put("PLAIN", new ThePlainSaslConnCtrl.Factory());
			map.put("+++PLAIN+++", new ThePlainSaslConnCtrl.Factory());

			this.mCtrlFactoryMap = map;
		}
		return map;
	}

	@Override
	public void onReceive(Xmpp_stream stream, Object object) {

		if (object instanceof Xmpp_features) {
			Xmpp_features features = (Xmpp_features) object;

			Xmpp_starttls startTLS = (Xmpp_starttls) features
					.findItemByClass(Xmpp_starttls.class);
			if (startTLS != null) {
				this.doStartTLS();
				return;
			}

			Xmpp_mechanisms mechanisms = (Xmpp_mechanisms) features
					.findItemByClass(Xmpp_mechanisms.class);
			if (mechanisms != null) {
				this.doSelectMechanisms(mechanisms, stream, object);
				return;
			}

		} else if (object instanceof Xmpp_proceed) {
			this.doCreateTLSConnect();
			return;
		}
		System.err.println(this + "::unprocessed rx object : " + object);
	}

	private void doSelectMechanisms(Xmpp_mechanisms mechanisms,
			Xmpp_stream stream, Object object) {

		List<Xmpp_mechanism> list = mechanisms.listItems();
		String name = null;
		IXmppConnectionControllerFactory fact = null;
		for (int i = list.size() - 1; i >= 0; i--) {
			Xmpp_mechanism item = list.get(i);

			name = item.getName();
			fact = this.getCtrlFactoryMap().get(name);
			System.out.println("          find " + item + " result : " + fact);
			if (fact != null) {
				break;
			}
		}
		XmppConnection conn = this.getConnection();
		IXmppConnectionController ctrl = fact.newController(conn);
		conn.setController(ctrl);
		ctrl.onReceive(stream, object);
	}

	private void doCreateTLSConnect() {
		XmppConnection conn = new XmppConnection(this.getConnection());
		conn.run();
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
