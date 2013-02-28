package test.ananas.lib.axk;

import java.util.ArrayList;
import java.util.List;

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

		List<XmppAccount> accountList = new ArrayList<XmppAccount>();
		{
			// 0: axktest@jabber.org ,normal
			DefaultXmppAccount account = new DefaultXmppAccount();
			account.address = new DefaultXmppAddress("axktest@jabber.org");
			account.password = "12345678";
			account.host = "jabber.org";
			account.resource = this.getClass().getName();
			account.useSSL = false;
			account.port = account.useSSL ? 5223 : 5222;
			accountList.add(account);
		}
		{
			// 1: axktest@jabber.org ,useSSL
			DefaultXmppAccount account = new DefaultXmppAccount();
			account.address = new DefaultXmppAddress("axktest@jabber.org");
			account.password = "12345678";
			account.host = "jabber.org";
			account.resource = this.getClass().getName();
			account.useSSL = true;
			account.port = account.useSSL ? 5223 : 5222;
			accountList.add(account);
		}
		{
			// 2: xk19850217@gmail.com ,normal
			DefaultXmppAccount account = new DefaultXmppAccount();
			account.address = new DefaultXmppAddress("xk19850217@gmail.com");
			account.password = "xk12345678";
			account.host = "talk.google.com";
			account.resource = this.getClass().getName();
			account.useSSL = false;
			account.port = account.useSSL ? 5223 : 5222;
			accountList.add(account);
		}
		{
			// 3: xk19850217@gmail.com ,useSSL
			DefaultXmppAccount account = new DefaultXmppAccount();
			account.address = new DefaultXmppAddress("xk19850217@gmail.com");
			account.password = "xk12345678";
			account.host = "talk.google.com";
			account.resource = this.getClass().getName();
			account.useSSL = true;
			account.port = account.useSSL ? 5223 : 5222;
			accountList.add(account);
		}
		XmppAccount account = accountList.get(2);

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
