package impl.ananas.axk2.core.base;

import ananas.axk2.core.XmppFilter;

public class Case {

	private final String _ns;
	private XmppFilter _filter;

	public Case(String ns) {
		_ns = ns;
	}

	public String getNamespace() {
		return this._ns;
	}

	public XmppFilter getFilter() {
		return this._filter;
	}

	public void setFilter(XmppFilter filter) {
		this._filter = filter;
	}

}
