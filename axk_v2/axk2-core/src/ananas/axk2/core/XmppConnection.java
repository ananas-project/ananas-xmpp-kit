package ananas.axk2.core;

public interface XmppConnection extends XmppCommandDispatcher,
		XmppEventDispatcher, XmppAPIProvider {

	XmppAccount getAccount();

	XmppContext getContext();

	XmppFilter getFilter();

	void close();

	XmppAPI getAPI(Class<?> apiClass);

}
