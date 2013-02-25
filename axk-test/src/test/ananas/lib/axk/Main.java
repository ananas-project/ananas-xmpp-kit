package test.ananas.lib.axk;

import ananas.lib.axk.DefaultXmppAccount;
import ananas.lib.axk.DefaultXmppAddress;
import ananas.lib.axk.XmppAccount;
import ananas.lib.axk.XmppClient;
import ananas.lib.axk.XmppClientFactory;
import ananas.lib.axk.XmppEnvironment;
import ananas.lib.axk.XmppEvent;
import ananas.lib.axk.XmppEventListener;
import ananas.lib.axk.XmppUtil;
import ananas.lib.axk.api.IExShell;
import ananas.lib.axk.command.TraceCommand;

public class Main implements XmppEventListener {

	public static void main(String[] arg) {

		Main main = new Main();
		main.run();

	}

	private void run() {

		System.out.println(this + ".begin");

		DefaultXmppAccount account = new DefaultXmppAccount();
		account.address = new DefaultXmppAddress("axktest@jabber.org/hw001");
		account.password = "12345678";
		account.host = "jabber.org";
		account.resource = this.getClass().getName();

		XmppEnvironment envi = XmppUtil.getDefaultEnvironment();
		XmppClientFactory factory = envi.getClientFactory();
		XmppClient client = factory.newClient(account);
		client.setXmppEventListener(this);

		// trace
		TraceCommand cmd = new TraceCommand();
		client.dispatch(cmd);

		IExShell shell = (IExShell) client.getExAPI(IExShell.class);

		// show account
		XmppAccount acc = shell.getAccount();
		System.out.println("account = " + acc);

		// connect
		shell.connect();

		// end
		System.out.println(this + ".end");
	}

	@Override
	public void onEvent(XmppEvent event) {
		System.out.println("onEvent:" + event);
	}
}
