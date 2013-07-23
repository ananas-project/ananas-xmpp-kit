package test.ananas.axk2;

import java.io.InputStream;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppConnector;
import ananas.axk2.core.XmppContext;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppEventListener;
import ananas.axk2.core.XmppStatus;
import ananas.axk2.core.api.IClient;
import ananas.axk2.core.event.BindEvent;
import ananas.axk2.core.event.PhaseEvent;
import ananas.axk2.core.event.StanzaEvent;
import ananas.axk2.core.util.XmppClientBuilder;
import ananas.lib.util.logging.Logger;

public class TestAxk2 {

	final static Logger log = Logger.Agent.getLogger("test");

	public static void main(String[] arg) {
		TestAxk2 test = new TestAxk2();
		test.run();
	}

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
		IClient client = (IClient) conn.getAPI(IClient.class);
		conn.addEventListener(new MyEventListener());

		log.debug("current phase : " + client.getPhase());
		client.connect();
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.debug("current phase : " + client.getPhase());

	}

	class MyEventListener implements XmppEventListener {

		@Override
		public void onEvent(XmppEvent event) {
			if (event instanceof BindEvent) {
				BindEvent be = (BindEvent) event;
				XmppAddress jid = be.getBind();
				log.debug("xmpp-bind-jid : " + jid);

			} else if (event instanceof PhaseEvent) {
				PhaseEvent pe = (PhaseEvent) event;
				XmppStatus pold = pe.getOldPhase();
				XmppStatus pnew = pe.getNewPhase();
				log.debug("xmpp-phase    : " + pold + " -> " + pnew);

			} else if (event instanceof StanzaEvent) {
				StanzaEvent se = (StanzaEvent) event;
				String s = se.getString();
				log.debug("xmpp-stanza   : " + s);

			} else {
				log.debug(this + ".onEvent : " + event);
			}
		}
	}

}
