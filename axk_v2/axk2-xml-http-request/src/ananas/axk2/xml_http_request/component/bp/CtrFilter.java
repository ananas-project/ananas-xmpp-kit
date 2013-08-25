package ananas.axk2.xml_http_request.component.bp;

import ananas.axk2.core.XmppFilter;

public class CtrFilter extends CtrObject {

	public XmppFilter target_Filter() {
		return (XmppFilter) this.getTarget(true);
	}

}
