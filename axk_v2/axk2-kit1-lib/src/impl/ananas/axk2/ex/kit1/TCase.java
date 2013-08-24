package impl.ananas.axk2.ex.kit1;

import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppFilter;

public class TCase extends TFilter {

	private String _ns;
	private XmppFilter _filter;

	public TCase() {
	}

	public void addFilter(XmppFilter filter) {
		if (this._filter == null) {
			this._filter = filter;
		} else {
			throw new RuntimeException("a TCase contain only one filter!");
		}
	}

	@Override
	public int listAPI(XmppAPIHandler h) {
		XmppFilter filter = this._filter;
		if (filter == null) {
			return super.listAPI(h);
		} else {
			return filter.listAPI(h);
		}
	}

	public String getCaseNamespace() {
		return this._ns;
	}

	public void setCaseNamespace(String ns) {
		this._ns = ns;
	}

}
