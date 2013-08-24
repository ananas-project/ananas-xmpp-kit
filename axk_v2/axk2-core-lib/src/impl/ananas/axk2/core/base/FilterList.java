package impl.ananas.axk2.core.base;

import java.util.ArrayList;
import java.util.List;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.XmppFilterList;

public class FilterList implements XmppFilterList {

	private final List<XmppFilter> _list;
	private XmppConnection _conn;
	private XmppFilter[] _cache;// [ local to remote ]

	public FilterList() {
		this._list = new ArrayList<XmppFilter>();
	}

	protected void onChanged() {
		this._cache = null;
	}

	@Override
	public int insert(XmppFilter filter, int index) {
		int sz = _list.size();
		if (index < sz) {
			_list.add(index, filter);
			this.onChanged();
			return index;
		} else {
			_list.add(filter);
			this.onChanged();
			return sz;
		}
	}

	@Override
	public int append(XmppFilter filter) {
		return this.insert(filter, 0xffff);
	}

	@Override
	public int count() {
		XmppFilter[] array = this.__getCache();
		return array.length;
	}

	@Override
	public XmppFilter get(int index) {
		XmppFilter[] array = this.__getCache();
		return array[index];
	}

	private XmppFilter[] __getCache() {
		XmppFilter[] array = this._cache;
		if (array == null) {
			array = this._list.toArray(new XmppFilter[_list.size()]);
			boolean needReverse = false;
			for (XmppFilter f : array) {
				if (f instanceof Local) {
					break;
				} else if (f instanceof Remote) {
					needReverse = true;
					break;
				}
			}
			if (needReverse) {
				int a = 0;
				int b = array.length - 1;
				for (; a < b; a++, b--) {
					final XmppFilter ofa = array[a];
					final XmppFilter ofb = array[b];
					array[a] = ofb;
					array[b] = ofa;
				}
			}
			this._cache = array;
		}
		return array;
	}

	@Override
	public boolean remove(int index) {
		XmppFilter obj = _list.remove(index);
		this.onChanged();
		return (obj != null);
	}

	@Override
	public boolean remove(XmppFilter filter) {
		boolean ret = _list.remove(filter);
		this.onChanged();
		return ret;
	}

	@Override
	public List<XmppFilter> listAll() {
		return new ArrayList<XmppFilter>(_list);
	}

	@Override
	public void bind(XmppConnection connection) {
		this._conn = connection;
		XmppFilter[] array = this.__getCache();
		for (XmppFilter f : array) {
			f.bind(connection);
		}
	}

	@Override
	public void unbind(XmppConnection connection) {
		this._conn = null;
		XmppFilter[] array = this.__getCache();
		for (XmppFilter f : array) {
			f.unbind(connection);
		}
	}

	@Override
	public XmppConnection getConnection() {
		return this._conn;
	}

	@Override
	public XmppCommand filter(XmppCommand command) {
		XmppFilter[] array = this.__getCache();
		for (XmppFilter f : array) {
			if (command == null)
				break;
			command = f.filter(command);
		}
		return command;
	}

	@Override
	public XmppEvent filter(XmppEvent event) {
		XmppFilter[] array = this.__getCache();
		for (int i = array.length - 1; i >= 0; i--) {
			XmppFilter f = array[i];
			if (event == null)
				break;
			event = f.filter(event);
		}
		return event;
	}

	@Override
	public int listAPI(XmppAPIHandler h) {
		XmppFilter[] array = this.__getCache();
		for (XmppFilter f : array) {
			if (f.listAPI(h) == XmppAPI.find_break)
				return XmppAPI.find_break;
		}
		return XmppAPI.find_continue;
	}

}
