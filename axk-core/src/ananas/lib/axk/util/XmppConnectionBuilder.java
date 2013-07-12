package ananas.lib.axk.util;

import java.io.InputStream;
import java.util.Properties;

import ananas.lib.axk.XmppAccount;
import ananas.lib.axk.XmppConnectionFactory;
import ananas.lib.axk.XmppEnvironment;

public interface XmppConnectionBuilder {

	XmppAccount loadAccount(InputStream in);

	XmppAccount loadAccount(Properties properties);

	XmppAccount loadAccount(String properties);

	XmppEnvironment loadEnvironment(InputStream in);

	XmppConnectionFactory loadConnectionFactory(InputStream in);

}
