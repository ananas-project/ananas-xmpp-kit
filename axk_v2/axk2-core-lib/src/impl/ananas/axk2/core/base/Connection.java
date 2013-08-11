package impl.ananas.axk2.core.base;

import ananas.axk2.core.DefaultXmppEventDispatcher;
import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppContext;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppEventDispatcher;
import ananas.axk2.core.XmppEventListener;
import ananas.axk2.core.XmppFilter;

public class Connection implements XmppConnection {

	private XmppAccount _account;
	private XmppContext _context;
	private XmppFilter _filter;
	private XmppConnection _facade;

	public Connection() {
	}

	@Override
	public boolean send(XmppCommand cmd) {
		XmppFilter filter = this._filter;
		if (filter == null) {
			return false;
		} else {
			filter.filter(cmd);
			return true;
		}
	}

	@Override
	public void dispatch(XmppEvent event) {
		XmppFilter filter = this._filter;
		if (filter != null) {
			filter.filter(event);
		}
	}

	@Override
	public XmppAccount getAccount() {
		return this._account;
	}

	@Override
	public XmppContext getContext() {
		return this._context;
	}

	@Override
	public void close() {
	}

	public XmppConnection getFacade() {
		XmppConnection facade = this._facade;
		if (facade == null) {
			this._facade = facade = new XmppConnectionFacade(this);
		}
		return facade;
	}

	public void init(XmppContext context, XmppAccount account) {
		this._account = account;
		this._context = context;
		final XmppFilter filter = this._filter;
		if (filter != null) {
			filter.bind(this.getFacade());
		}
	}

	private final XmppEventDispatcher _event_disp = new DefaultXmppEventDispatcher();

	@Override
	public void addEventListener(XmppEventListener listener) {
		this._event_disp.addEventListener(listener);
	}

	@Override
	public void removeEventListener(XmppEventListener listener) {
		this._event_disp.removeEventListener(listener);
	}

	public void setFilter(XmppFilter filter) {
		this._filter = filter;
	}

	@Override
	public XmppFilter getFilter() {
		return this._filter;
	}

	@Override
	public int findAPI(Class<?> apiClass, XmppAPIHandler h) {
		XmppFilter filter = this._filter;
		if (filter == null) {
			return XmppAPIHandler.find_continue;
		} else {
			return filter.findAPI(apiClass, h);
		}
	}

	@Override
	public XmppAPI getAPI(Class<?> apiClass) {
		XmppFilter filter = this._filter;
		if (filter == null) {
			return null;
		} else {
			class MyHandler implements XmppAPIHandler {
				XmppAPI _api;

				@Override
				public int onAPI(Class<?> apiClass, XmppAPI api) {
					if (api == null) {
						return XmppAPI.find_continue;
					} else {
						_api = api;
						return XmppAPI.find_break;
					}
				}
			}
			MyHandler h = new MyHandler();
			filter.findAPI(apiClass, h);
			return h._api;
		}
	}
}
