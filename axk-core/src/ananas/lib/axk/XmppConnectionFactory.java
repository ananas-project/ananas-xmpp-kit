package ananas.lib.axk;

public interface XmppConnectionFactory {

	XmppConnection openConnection(XmppEnvironment envi, XmppAccount account);

}
