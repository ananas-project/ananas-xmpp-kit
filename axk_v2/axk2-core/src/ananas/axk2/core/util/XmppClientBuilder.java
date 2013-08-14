package ananas.axk2.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppConnector;
import ananas.axk2.core.XmppContext;
import ananas.lib.util.SingletonLoader;

public interface XmppClientBuilder {

	// set account

	String key_jid = "jid";
	String key_host = "host";
	String key_port = "port";
	String key_resource = "resource";
	String key_password = "password";
	String key_use_ssl = "use_ssl";
	String key_ignore_ssl_error = "ignore_ssl_error";

	XmppClientBuilder setJID(String jid);

	XmppClientBuilder setPassword(String password);

	XmppClientBuilder setResource(String resource);

	XmppClientBuilder setHost(String host);

	XmppClientBuilder setPort(int port);

	XmppClientBuilder setUseSSL(boolean useSSL);

	XmppClientBuilder loadAccount(Properties prop);

	XmppClientBuilder loadAccount(InputStream in);

	XmppClientBuilder loadAccount(Object ref, String fileName);

	XmppClientBuilder loadConfigXML(Object ref, String fileName);

	// set config

	XmppClientBuilder loadConfigXML(InputStream in) throws SAXException,
			IOException, ParserConfigurationException;

	// create end-point

	XmppConnection createClient();

	XmppConnector getConnector();

	XmppAccount getAccount();

	XmppContext getContext();

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
