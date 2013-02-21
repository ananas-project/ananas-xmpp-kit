package test.ananas.lib.axk;

import ananas.lib.axk.DefaultXmppAccount;
import ananas.lib.axk.XmppAccount;
import ananas.lib.axk.XmppAddress;
import ananas.lib.axk.XmppClient;
import ananas.lib.axk.XmppClientFactory;
import ananas.lib.axk.XmppEvent;
import ananas.lib.axk.XmppEventListener;
import ananas.lib.axk.XmppPhase;
import ananas.lib.axk.XmppUtil;
import ananas.lib.axk.api.IExConnection;
import ananas.lib.axk.command.TraceCommand;

public class Main implements XmppEventListener {

	public static void main(String[] arg) {

		Main main = new Main();
		main.run();

	}

	private void run() {

		System.out.println(this + ".begin");

		DefaultXmppAccount account = new DefaultXmppAccount();
		account.address = new XmppAddress("xk@fuyoo.net/hw001");
		account.password = "1234";
		account.host = "jabber.org";

		XmppClientFactory factory = XmppUtil.getFactory();
		XmppClient client = factory.newClient(account);
		client.setXmppEventListener(this);

		// trace
		TraceCommand cmd = new TraceCommand();
		client.dispatch(cmd);

		// show account
		XmppAccount acc = client.getAccount();
		System.out.println("account = " + acc);

		// connect
		IExConnection conn = (IExConnection) client
				.getExAPI(IExConnection.class);
		conn.setStatus(XmppPhase.online);

		// end
		System.out.println(this + ".end");
	}

	@Override
	public void onEvent(XmppEvent event) {
		System.out.println("onEvent:" + event);
	}
}
