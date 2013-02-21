package ananas.lib.axk;

public interface XmppAccount {

	String host();

	int port();

	String password();

	XmppAddress address();

	String resource();

	boolean useSSL();

	boolean ignoreSSLError();
}
