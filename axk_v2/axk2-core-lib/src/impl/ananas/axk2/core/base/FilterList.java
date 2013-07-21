package impl.ananas.axk2.core.base;

import java.util.ArrayList;
import java.util.List;

import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.XmppFilterManager;

public class FilterList implements XmppFilterManager {

	private final List<XmppFilter> _list;

	public FilterList() {
		this._list = new ArrayList<XmppFilter>();
	}

	protected void onChanged() {
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
		int ret = this.insert(filter, 0xffff);
		this.onChanged();
		return ret;
	}

	@Override
	public int count() {
		return this._list.size();
	}

	@Override
	public XmppFilter get(int index) {
		return _list.get(index);
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

}
