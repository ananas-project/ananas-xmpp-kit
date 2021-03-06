package impl.ananas.axk2.ex.kit1.bp;

import impl.ananas.axk2.ex.kit1.TCase;
import ananas.axk2.core.XmppFilter;
import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.core.lang.BPNode;

public class CCase extends CtrObject {

	public TCase target_case() {
		return (TCase) this.getTarget(true);
	}

	@Override
	protected boolean onAppendChild(BPNode node) {
		if (node instanceof BPElement) {
			Object tar = ((BPElement) node).getTarget(true);
			if (tar instanceof XmppFilter) {
				XmppFilter filter = (XmppFilter) tar;
				this.target_case().addFilter(filter);
				return true;
			}
		}
		return super.onAppendChild(node);
	}

	@Override
	protected boolean onSetAttribute(String uri, String localName, String value) {
		if (localName.equals("namespace")) {
			this.target_case().setCaseNamespace(value);
			return true;
		}
		return super.onSetAttribute(uri, localName, value);
	}

}
