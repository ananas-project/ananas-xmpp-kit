package ananas.axk2.core;

public interface XmppAPIProvider {

	XmppAPI getAPI(Class<?> apiClass);

	XmppAPI getAPI(Class<?> apiClass, Object after);

}
