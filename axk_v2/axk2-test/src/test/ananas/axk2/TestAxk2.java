package test.ananas.axk2;

import java.io.InputStream;

import test.ananas.axk2.util.testing.ITest;
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
import ananas.axk2.ex.kit1.ITerminalAgent;
import ananas.blueprint4.terminal.Terminal;
import ananas.lib.task.Task;
import ananas.lib.task.TaskManager;
import ananas.lib.util.logging.Logger;

public class TestAxk2 implements ITest {

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
		// client.connect();
		client.disconnect();

		ITerminalAgent terAgent = (ITerminalAgent) conn
				.getAPI(ITerminalAgent.class);
		Terminal termi = terAgent.getTerminal();
		String cmd_init = System
				.getProperty("axk2.connection.terminal.command.init");
		if (cmd_init != null) {
			termi.execute(cmd_init);
		}
		// termi.getRunnable().run();
		Task task = new MyTask(termi);
		TaskManager.Agent.getInstance().run(task);

		client.disconnect();
	}

	class MyTask implements Task {

		private final Terminal _termi;

		public MyTask(Terminal termi) {
			this._termi = termi;
		}

		@Override
		public void run() {
			this._termi.getRunnable().run();
		}

		@Override
		public void cancel() {
			this._termi.execute("exit");
		}
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
