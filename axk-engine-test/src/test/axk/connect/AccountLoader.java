package test.axk.connect;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ananas.lib.axk.engine.XAccount;
import ananas.lib.axk.engine.util.DefaultXAccount;

public class AccountLoader {

	private String mFileName = "account.properties";

	public XAccount load() {
		try {
			InputStream in = this.getClass()
					.getResourceAsStream(this.mFileName);
			Properties prop = new Properties();
			prop.load(in);
			DefaultXAccount account = new DefaultXAccount();
			account.jid = this.getString(prop, "jid");
			account.host = this.getString(prop, "host");
			account.port = this.getInt(prop, "port");
			account.password = this.getString(prop, "password");
			account.resource = this.getString(prop, "resource")
					+ System.currentTimeMillis();
			account.useSSL = this.getBoolean(prop, "useSSL");
			return account;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private int getInt(Properties prop, String key) {
		String s = prop.getProperty(key);
		return Integer.parseInt(s);
	}

	private boolean getBoolean(Properties prop, String key) {
		String s = prop.getProperty(key);
		return Boolean.parseBoolean(s);
	}

	private String getString(Properties prop, String key) {
		return prop.getProperty(key);
	}

}
