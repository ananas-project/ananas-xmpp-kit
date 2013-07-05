package test.axk.connect;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import ananas.lib.axk.engine.XContext;
import ananas.lib.axk.engine.XContextFactory;
import ananas.lib.axk.engine.XEngineListener;
import ananas.lib.axk.engine.util.DefaultXAccount;

public class TestAxkConnect {

	public static void main(String[] args) {

		try {
			DefaultXAccount account = new DefaultXAccount();

			account.jid = "xukun@jabber.org";
			account.host = "jabber.org";
			account.port = 5222;
			account.useSSL = false;
			account.resource = "abcd";
			account.password = "12345678";

			XEngineListener listener = new MyListener();

			XContext context = XContextFactory.Util.getFactory().createContext(
					account, listener);

			context.getEngineFactory().createEngine().run(context);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static class MyListener implements XEngineListener {

		@Override
		public void onStanzaElement(XContext context, Element element) {
			DOMImplementation impl = element.getOwnerDocument()
					.getImplementation();
			DOMImplementationLS ls = (DOMImplementationLS) impl.getFeature(
					"LS", "3.0");
			LSSerializer seri = ls.createLSSerializer();
			System.out.println(this + ".onElement:"
					+ seri.writeToString(element));

		}
	}
}
