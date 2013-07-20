package ananas.axk2.engine;

import java.net.InetSocketAddress;
import java.security.GeneralSecurityException;

import javax.net.ssl.SSLContext;

import ananas.axk2.core.XmppAccount;

public interface XEngineConnector {

	SSLContext getSSLContext() throws GeneralSecurityException;

	// void connect(Socket sock, XmppAccount account);

	InetSocketAddress getAddressByAccount(XmppAccount account);

}
