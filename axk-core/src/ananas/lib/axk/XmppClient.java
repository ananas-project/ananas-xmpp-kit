package ananas.lib.axk;

public interface XmppClient extends XmppCommandDispatcher {

	XmppEnvironment getEnvironment();

	void setXmppEventListener(XmppEventListener listener);

	XmppAccount getAccount();

	XmppClientExAPI getExAPI(Class<? extends XmppClientExAPI> apiClass);

}
