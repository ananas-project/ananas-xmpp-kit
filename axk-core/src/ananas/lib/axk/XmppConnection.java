package ananas.lib.axk;

import java.util.List;

public interface XmppConnection extends XmppCommandDispatcher,
		XmppEventDispatcher {

	Object getAPI(Class<?> apiClass);

	void loadFilter(XmppFilter filter, int index);

	void unloadFilter(XmppFilter filter);

	void unloadFilter(int index);

	List<XmppFilter> listFilter();

	void close();
}
