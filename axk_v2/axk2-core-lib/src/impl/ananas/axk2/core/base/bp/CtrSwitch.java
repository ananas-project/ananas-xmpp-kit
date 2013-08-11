package impl.ananas.axk2.core.base.bp;

import impl.ananas.axk2.core.base.Case;
import impl.ananas.axk2.core.base.Switch;
import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.core.lang.BPNode;

public class CtrSwitch extends CtrObject {

	public Switch target_switch() {
		return (Switch) this.getTarget(true);
	}

	@Override
	protected boolean onAppendChild(BPNode node) {
		if (node instanceof BPElement) {
			Object ch_tar = ((BPElement) node).getTarget(true);
			if (ch_tar instanceof Case) {
				Switch pt_tar = this.target_switch();
				pt_tar.addCase((Case) ch_tar);
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
