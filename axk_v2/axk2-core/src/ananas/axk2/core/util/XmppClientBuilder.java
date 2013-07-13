package ananas.axk2.core.util;

import java.io.InputStream;
import java.util.Properties;

import ananas.axk2.core.XmppConnection;
import ananas.lib.util.SingletonLoader;

public interface XmppClientBuilder {

	// set account

	String key_jid = "jid";
	String key_host = "host";
	String key_port = "port";
	String key_resource = "resource";
	String key_password = "password";
	String key_use_ssl = "use_ssl";

	XmppClientBuilder setJID(String jid);

	XmppClientBuilder setPassword(String password);

	XmppClientBuilder setResource(String resource);

	XmppClientBuilder setHost(String host);

	XmppClientBuilder setPort(int port);

	XmppClientBuilder setUseSSL(boolean useSSL);

	XmppClientBuilder loadAccount(Properties prop);

	XmppClientBuilder loadAccount(InputStream in);

	// set config

	XmppClientBuilder loadConfigXML(InputStream in);

	// create end-point
	XmppConnection createClient();

	class Factory {

		public static XmppClientBuilder getDefault() {
			return (XmppClientBuilder) SingletonLoader
					.load(XmppClientBuilder.class);
		}

		public static XmppClientBuilder getInstance(String className) {
			return (XmppClientBuilder) SingletonLoader.load(className);
		}

	}
}
