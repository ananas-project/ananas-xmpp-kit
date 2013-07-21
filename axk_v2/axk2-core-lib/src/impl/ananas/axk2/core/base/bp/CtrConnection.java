package impl.ananas.axk2.core.base.bp;

import impl.ananas.axk2.core.base.Connection;
import impl.ananas.axk2.core.base.FilterList;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.XmppFilterManager;
import ananas.blueprint4.core.lang.BPNode;

public class CtrConnection extends CtrObject {

	public Connection target_Connection() {
		return (Connection) this.getTarget(true);
	}

	@Override
	protected boolean onAppendChild(BPNode node) {
		if (node instanceof CtrFilterList) {
			final Connection p = this.target_Connection();
			final FilterList c = ((CtrFilterList) node).target_FilterList();
			final XmppFilterManager fm = p.getFilterManager();
			for (XmppFilter filter : fm.listAll()) {
				fm.remove(filter);
			}
			for (XmppFilter filter : c.listAll()) {
				fm.append(filter);
			}
		} else {
			return super.onAppendChild(node);
		}
		return true;
	}

}
