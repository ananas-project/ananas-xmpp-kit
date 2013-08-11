package ananas.axk2.core;

public interface XmppAPIProvider extends XmppAPI {

	/**
	 * @return XmppAPI.find_break|XmppAPI.find_continue
	 * */

	int findAPI(Class<?> apiClass, XmppAPIHandler h);

}
