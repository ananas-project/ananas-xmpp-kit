package ananas.axk2.core;

public interface XmppConnection extends XmppCommandDispatcher,
		XmppEventDispatcher, XmppAPIProvider {

	XmppAccount getAccount();

	XmppEnvironment getEnvironment();

	XmppFilterManager getFilterManager();

	void close();

}
