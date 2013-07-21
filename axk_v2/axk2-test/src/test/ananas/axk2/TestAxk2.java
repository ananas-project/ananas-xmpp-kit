package test.ananas.axk2;

import java.io.InputStream;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppConnector;
import ananas.axk2.core.XmppContext;
import ananas.axk2.core.api.IClient;
import ananas.axk2.core.util.XmppClientBuilder;
import ananas.lib.util.PropertiesLoader;

public class TestAxk2 {

	public static void main(String[] arg) {
		TestAxk2 test = new TestAxk2();
		test.run();
	}

	private void run() {

		PropertiesLoader.Util.loadPropertiesToSystem(this, "system.properties");

		XmppClientBuilder xcb = XmppClientBuilder.Factory.getDefault();

		try {
			InputStream in = this.getClass().getResourceAsStream(
					"account.properties");
			xcb.loadAccount(in);
			in.close();
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
		client.connect();
	}

}
