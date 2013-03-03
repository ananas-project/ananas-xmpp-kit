package ananas.lib.impl.axk.client.conn;

public interface XmppConnectionListener extends ITxThreadPriority {

	/**
	 * all of these methods invoked by rx thread
	 * */

	void onReceive(XmppConnection conn, Object object);

	void invokeWithTxThread(XmppConnection conn, Runnable runn, int priority);

	void onSetCurrentConnection(XmppConnection conn);

}
