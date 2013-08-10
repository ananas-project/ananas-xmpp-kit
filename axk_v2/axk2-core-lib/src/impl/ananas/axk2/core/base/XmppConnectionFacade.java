package impl.ananas.axk2.core.base;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppContext;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppEventListener;
import ananas.axk2.core.XmppFilter;

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

	public XmppAccount getAccount() {
		return _inner.getAccount();
	}

	public void close() {
		_inner.close();
	}

	@Override
	public XmppContext getContext() {
		return _inner.getContext();
	}

	@Override
	public void addEventListener(XmppEventListener listener) {
		_inner.addEventListener(listener);
	}

	@Override
	public void removeEventListener(XmppEventListener listener) {
		_inner.removeEventListener(listener);
	}

	@Override
	public XmppFilter getFilter() {
		return _inner.getFilter();
	}

	@Override
	public void setFilter(XmppFilter filter) {
		_inner.setFilter(filter);
	}

	@Override
	public int findAPI(Class<?> apiClass, XmppAPIHandler h) {
		return _inner.findAPI(apiClass, h);
	}

}
