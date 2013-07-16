package ananas.axk2.core;

public interface XmppAccount {

	XmppAddress jid();

	String resource();

	String password();

	String host();

	int port();

	boolean useSSL();

	boolean ignoreTLSError();
}
