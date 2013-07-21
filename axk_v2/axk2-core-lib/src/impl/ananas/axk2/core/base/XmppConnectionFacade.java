package impl.ananas.axk2.core.base;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppContext;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilterManager;

public class XmppConnectionFacade implements XmppConnection {

	private final XmppConnection _inner;

	public XmppConnectionFacade(XmppConnection inner) {
		this._inner = inner;
	}

	public XmppAPI getAPI(Class<?> apiClass) {
		return _inner.getAPI(apiClass);
	}

	public void dispatch(XmppEvent event) {
		_inner.dispatch(event);
	}

	public boolean send(XmppCommand cmd) {
		return _inner.send(cmd);
	}

	public XmppAPI getAPI(Class<?> apiClass, Object after) {
		return _inner.getAPI(apiClass, after);
	}

	public XmppAccount getAccount() {
		return _inner.getAccount();
	}

	public XmppFilterManager getFilterManager() {
		return _inner.getFilterManager();
	}

	public void close() {
		_inner.close();
	}

	@Override
	public XmppContext getContext() {
		return _inner.getContext();
	}

}
