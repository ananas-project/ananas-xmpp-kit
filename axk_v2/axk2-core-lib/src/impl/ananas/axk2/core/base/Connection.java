package impl.ananas.axk2.core.base;

import java.util.List;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppContext;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.XmppFilterManager;

public class Connection implements XmppConnection {

	private XmppAccount _account;
	private XmppContext _context;
	private final XmppFilterManager _fm = new MyFM();
	private XmppFilter[] _filter_list;
	private XmppConnection _facade;

	@Override
	public boolean send(XmppCommand cmd) {
		XmppFilter[] list = this.__getFilterList();
		for (XmppFilter f : list) {
			if (cmd == null) {
				break;
			} else {
				cmd.onSendBy(f);
				cmd = f.filter(cmd);
			}
		}
		return (cmd != null);
	}

	@Override
	public void dispatch(XmppEvent event) {
		XmppFilter[] list = this.__getFilterList();
		for (int i = list.length - 1; i >= 0; i--) {
			XmppFilter f = list[i];
			if (event == null) {
				break;
			} else {
				event.onReceiveBy(f);
				event = f.filter(event);
			}
		}
	}

	/**
	 * [0,n]::[local,remote]
	 * */
	private XmppFilter[] __getFilterList() {
		XmppFilter[] array = this._filter_list;
		if (array == null) {
			List<XmppFilter> list = this.getFilterManager().listAll();
			array = list.toArray(new XmppFilter[list.size()]);
			boolean needReverse = false;
			for (XmppFilter ft : array) {
				if (ft instanceof Remote) {
					needReverse = true;
					break;
				} else if (ft instanceof Local) {
					break;
				} else {
				}
			}
			if (needReverse) {
				int a = 0;
				int b = array.length - 1;
				for (; a < b; a++, b--) {
					XmppFilter pa = array[a];
					XmppFilter pb = array[b];
					array[a] = pb;
					array[b] = pa;
				}
			}
			this.__setFilterList(array);
		}
		return array;
	}

	private void __setFilterList(XmppFilter[] list) {
		XmppFilter[] pold;
		synchronized (this) {
			pold = this._filter_list;
			this._filter_list = list;
		}
		if (pold != null) {
			for (XmppFilter ft : pold) {
				ft.unbind(this.getFacade());
			}
		}
		if (list != null) {
			for (XmppFilter ft : list) {
				ft.bind(this.getFacade());
			}
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
	public XmppFilterManager getFilterManager() {
		return this._fm;
	}

	@Override
	public void close() {
	}

	class MyFM extends FilterList {

		@Override
		protected void onChanged() {
			Connection.this.__setFilterList(null);
		}

	}

	@Override
	public XmppAPI getAPI(Class<?> apiClass, Object after) {
		XmppFilter[] list = this.__getFilterList();
		for (XmppFilter f : list) {
			if (after == null) {
				XmppAPI api = f.getAPI(apiClass);
				if (api != null) {
					return api;
				}
			} else {
				if (after.equals(f)) {
					after = null;
				}
			}
		}
		return null;
	}

	@Override
	public XmppAPI getAPI(Class<?> apiClass) {
		return this.getAPI(apiClass, null);
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
	}

}
