package ananas.lib.axk;

public interface XmppFilter {

	XmppCommand filter(XmppCommand command);

	XmppEvent filter(XmppEvent event);

	void onLoad(XmppConnection conn);

	void onUnload(XmppConnection conn);

	Object getAPI(Class<?> apiClass);

}
