package ananas.axk2.xml_http_request.component.bp;

import ananas.axk2.core.XmppFilter;

public class Tmapping {

	private String _url_pattern;
	private XmppFilter _filter;

	public void setUrlPattern(String url) {
		this._url_pattern = url;
	}

	public String getUrlPattern() {
		return this._url_pattern;
	}

	public void setFilter(XmppFilter filter) {
		this._filter = filter;
	}

	public XmppFilter getFilter() {
		return this._filter;
	}

}
