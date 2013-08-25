package ananas.axk2.xml_http_request.component.bp;

import ananas.axk2.core.XmppFilter;
import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.core.lang.BPNode;

public class Cmapping extends CtrObject {

	public Tmapping target_mapping() {
		return (Tmapping) this.getTarget(true);
	}

	protected boolean onAppendChild(BPNode node) {
		if (node instanceof BPElement) {
			Object tar = ((BPElement) node).getTarget(true);
			if (tar instanceof XmppFilter) {
				XmppFilter filter = (XmppFilter) tar;
				this.target_mapping().setFilter(filter);
				return true;
			}
		}
		return super.onAppendChild(node);
	}

	protected boolean onSetAttribute(String uri, String localName, String value) {
		if ("url-pattern".equals(localName)) {
			this.target_mapping().setUrlPattern(value);
		} else {
			return super.onSetAttribute(uri, localName, value);
		}
		return true;
	}

}
