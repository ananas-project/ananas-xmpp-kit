package ananas.axk2.xml_http_request.xmpp.bp;

import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.core.lang.BPNode;

public class Crequest extends CtrObject {

	public Trequest target_request() {
		return (Trequest) this.getTarget(true);
	}

	@Override
	protected boolean onSetAttribute(String uri, String localName, String value) {
		if ("method".equals(localName)) {
			this.target_request().setMethod(value);
		} else if ("url".equals(localName)) {
			this.target_request().setURL(value);
		} else if ("version".equals(localName)) {
			this.target_request().setVersion(value);
		} else {
			return super.onSetAttribute(uri, localName, value);
		}
		return true;
	}

	@Override
	protected boolean onAppendChild(BPNode node) {
		if (node instanceof BPElement) {
			Object tar = ((BPElement) node).getTarget(true);
			if (tar instanceof Theader) {
				Theader header = (Theader) tar;
				this.target_request().setHeader(header);
				return true;
			} else if (tar instanceof Tcontent) {
				Tcontent content = (Tcontent) tar;
				this.target_request().setContent(content);
				return true;
			}
		}
		return super.onAppendChild(node);
	}

}
