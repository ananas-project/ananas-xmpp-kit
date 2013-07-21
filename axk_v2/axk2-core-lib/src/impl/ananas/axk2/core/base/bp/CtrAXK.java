package impl.ananas.axk2.core.base.bp;

import impl.ananas.axk2.core.base.AXK;
import impl.ananas.axk2.core.base.Connection;
import ananas.blueprint4.core.lang.BPNode;

public class CtrAXK extends CtrObject {

	public AXK target_axk() {
		return (AXK) this.getTarget(true);
	}

	protected boolean onAppendChild(BPNode node) {
		if (node instanceof CtrConnection) {
			Connection child = ((CtrConnection) node).target_Connection();
			AXK parent = this.target_axk();
			parent.setConnection(child);
		} else {
			return super.onAppendChild(node);
		}
		return true;
	}
}
