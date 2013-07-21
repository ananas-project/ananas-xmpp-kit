package ananas.axk2.core;

public interface XmppConnection extends XmppCommandDispatcher,
		XmppEventDispatcher, XmppAPIProvider {

	XmppAccount getAccount();

	XmppContext getContext();

	XmppFilterManager getFilterManager();

	void close();

}
