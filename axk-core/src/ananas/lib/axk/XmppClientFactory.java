package ananas.lib.axk;

public interface XmppClientFactory {

	XmppClient newClient(XmppAccount account);

	XmppEnvironment getEnvironment();

	void setEnvironment(XmppEnvironment envi);

}
