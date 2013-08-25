package ananas.axk2.xml_http_request.component.bp;

import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.core.lang.BPNode;

public class CXMLHttpRequestService extends CtrFilter {

	public TXMLHttpRequestService target_service() {
		return (TXMLHttpRequestService) this.getTarget(true);
	}

	@Override
	protected boolean onAppendChild(BPNode node) {
		if (node instanceof BPElement) {
			Object ch_tar = ((BPElement) node).getTarget(true);
			if (ch_tar instanceof Tmapping) {
				Tmapping mapping = (Tmapping) ch_tar;
				this.target_service().add(mapping);
				return true;
			}
		}
		return super.onAppendChild(node);
	}

	@Override
	protected boolean onSetAttribute(String uri, String localName, String value) {
		return super.onSetAttribute(uri, localName, value);
	}

}
