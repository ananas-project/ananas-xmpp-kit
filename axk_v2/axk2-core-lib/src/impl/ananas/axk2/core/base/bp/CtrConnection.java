package impl.ananas.axk2.core.base.bp;

import impl.ananas.axk2.core.base.Connection;
import ananas.axk2.core.XmppFilter;
import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.core.lang.BPNode;

public class CtrConnection extends CtrObject {

	public Connection target_Connection() {
		return (Connection) this.getTarget(true);
	}

	@Override
	protected boolean onAppendChild(BPNode node) {
		if (node instanceof BPElement) {
			Object tar = ((BPElement) node).getTarget(true);
			if (tar instanceof XmppFilter) {
				XmppFilter filter = (XmppFilter) tar;
				this.target_Connection().setFilter(filter);
				return true;
			}
		}
		return super.onAppendChild(node);
	}

}
