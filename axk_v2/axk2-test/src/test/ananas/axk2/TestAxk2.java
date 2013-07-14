package test.ananas.axk2;

import java.io.InputStream;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.api.XapiClient;
import ananas.axk2.core.util.XmppClientBuilder;
import ananas.lib.util.PropertiesLoader;

public class TestAxk2 {

	public static void main(String[] arg) {
		TestAxk2 test = new TestAxk2();
		test.run();
	}

	private void run() {

		PropertiesLoader spl = new PropertiesLoader();
		spl.loadPropertiesToSystem(this, "system.properties");

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
			// xcb.loadConfigXML(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		XmppConnection conn = xcb.createClient();
		XapiClient client = (XapiClient) conn.getAPI(XapiClient.class);
		client.connect();
	}

}
