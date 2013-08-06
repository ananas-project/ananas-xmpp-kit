package impl.ananas.axk2.ex.kit1;

import java.util.ArrayList;
import java.util.List;

import ananas.axk2.core.XmppFilter;

public class TCase extends TFilter {

	private String _ns;
	private final List<XmppFilter> _filter_list;

	public TCase() {
		this._filter_list = new ArrayList<XmppFilter>();
	}

	public void addFilter(XmppFilter filter) {
		this._filter_list.add(filter);
	}

	public String getCaseNamespace() {
		return this._ns;
	}

	public void setCaseNamespace(String ns) {
		this._ns = ns;
	}

}
