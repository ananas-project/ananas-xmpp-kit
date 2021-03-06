package test.ananas.axk2;

import java.io.InputStream;

import test.ananas.axk2.util.testing.ITest;
import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppConnector;
import ananas.axk2.core.XmppContext;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppEventListener;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.XmppStatus;
import ananas.axk2.core.event.BindEvent;
import ananas.axk2.core.event.PhaseEvent;
import ananas.axk2.core.event.StanzaEvent;
import ananas.axk2.core.util.XmppClientBuilder;
import ananas.lib.util.logging.Logger;

public class TestAxk2ListAPI implements ITest {

	final static Logger log = Logger.Agent.getLogger("test");

	private void run() {

		XmppClientBuilder xcb = XmppClientBuilder.Factory.getDefault();

		try {
			InputStream in = this.getClass().getResourceAsStream(
					"account.properties");
			xcb.loadAccount(in);
			in.close();

			String res = xcb.getAccount().resource();
			xcb.setResource(res + "." + System.currentTimeMillis());

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			InputStream in = this.getClass().getResourceAsStream("config.xml");
			xcb.loadConfigXML(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		XmppAccount account = xcb.getAccount();
		XmppContext context = xcb.getContext();
		XmppConnector connector = xcb.getConnector();
		XmppConnection conn = connector.openConnection(context, account);

		XmppAPIHandler h = new XmppAPIHandler() {

			@Override
			public int onAPI(Class<?> apiClass, XmppAPI api) {

				if (apiClass.equals(XmppAPI.class)
						|| apiClass.equals(XmppFilter.class)) {
					return XmppAPI.find_continue;
				}

				String apistr = apiClass
						+ "                                                                   ";
				int limit = 64;
				if (apistr.length() > limit) {
					apistr = apistr.substring(0, limit);
				}
				System.out.println("    api:" + apistr + " of " + api);
				return XmppAPI.find_continue;
			}
		};
		System.out.println("list api::begin");
		conn.listAPI(h);
		System.out.println("list api::end");

	}

	class MyEventListener implements XmppEventListener {

		@Override
		public void onEvent(XmppEvent event) {
			if (event instanceof BindEvent) {
				BindEvent be = (BindEvent) event;
				XmppAddress jid = be.getBind();
				log.debug(this + ":xmpp-bind-jid : " + jid);

			} else if (event instanceof PhaseEvent) {
				PhaseEvent pe = (PhaseEvent) event;
				XmppStatus pold = pe.getOldPhase();
				XmppStatus pnew = pe.getNewPhase();
				log.debug(this + ":xmpp-phase    : " + pold + " -> " + pnew);

			} else if (event instanceof StanzaEvent) {
				StanzaEvent se = (StanzaEvent) event;
				String s = se.getString();
				log.debug(this + ":xmpp-stanza   : " + s);
				String tab = "    ";
				log.debug(tab + "      id=" + se.get_id());
				log.debug(tab + "    type=" + se.get_type());
				log.debug(tab + "    from=" + se.get_from());
				log.debug(tab + "      to=" + se.get_to());
				log.debug(tab + "xml:lang=" + se.get_xml_lang());

				Object obj = se.getObject();
				log.debug(tab + "root-obj=" + obj);

			} else {
				log.debug(this + ".onEvent : " + event);
			}
		}
	}

	@Override
	public void test() {
		this.run();
	}

}
