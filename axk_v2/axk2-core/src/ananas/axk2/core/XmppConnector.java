package ananas.axk2.core;

public interface XmppConnector {

	XmppConnection openConnection(XmppContext context, XmppAccount account);

}
