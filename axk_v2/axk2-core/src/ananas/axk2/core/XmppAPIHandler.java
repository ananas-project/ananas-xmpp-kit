package ananas.axk2.core;

public interface XmppAPIHandler extends XmppAPI {

	/**
	 * @return XmppAPI.find_break|XmppAPI.find_continue
	 * */

	int onAPI(Class<?> apiClass, XmppAPI api);
}
