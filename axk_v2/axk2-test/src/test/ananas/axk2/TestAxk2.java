package test.ananas.axk2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.api.XapiClient;
import ananas.axk2.core.util.XmppClientBuilder;

public class TestAxk2 {

	public static void main(String[] arg) {

		TestAxk2 test = new TestAxk2();
		test.run();
	}

	private void run() {

		this.loadImplementationMapping();

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
			InputStream in = this.getClass().getResourceAsStream(
					"config.properties");
			xcb.loadConfigXML(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		XmppConnection conn = xcb.createClient();
		XapiClient client = (XapiClient) conn.getAPI(XapiClient.class);
		client.connect();
	}

	private void loadImplementationMapping() {

		try {
			String name = "api-impl.properties";
			InputStream in = this.getClass().getResourceAsStream(name);
			Properties prop = new Properties();
			prop.load(in);
			in.close();

			// print all
			System.getProperties().putAll(prop);
			Set<Object> keys = System.getProperties().keySet();
			List<String> list = new ArrayList<String>();
			for (Object k : keys) {
				list.add(k.toString());
			}
			Collections.sort(list);
			for (String key : list) {
				String value = System.getProperty(key);
				System.out.println(key + "    =    " + value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
