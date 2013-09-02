package ananas.axk2.xml_http_request.xmpp.bp;

import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.core.lang.BPNode;

public class Cheader extends CtrObject {

	public Theader target_header() {
		return (Theader) this.getTarget(true);
	}

	@Override
	protected boolean onAppendChild(BPNode node) {
		if (node instanceof BPElement) {
			Object tar = ((BPElement) node).getTarget(true);
			if (tar instanceof Tfield) {
				this.target_header().setField((Tfield) tar);
				return true;
			}
		}
		return super.onAppendChild(node);
	}

}
