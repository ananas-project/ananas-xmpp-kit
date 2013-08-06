package impl.ananas.axk2.ex.kit1.bp;

import impl.ananas.axk2.ex.kit1.TCase;
import impl.ananas.axk2.ex.kit1.TSwitch;
import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.core.lang.BPNode;

public class CSwitch extends CtrObject {

	public TSwitch target_case() {
		return (TSwitch) this.getTarget(true);
	}

	@Override
	protected boolean onAppendChild(BPNode node) {
		if (node instanceof BPElement) {
			Object tar = ((BPElement) node).getTarget(true);
			if (tar instanceof TCase) {
				TCase tc = (TCase) tar;
				this.target_case().addCase(tc);
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
