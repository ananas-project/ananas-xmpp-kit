package impl.ananas.axk2.core.base.bp;

import impl.ananas.axk2.core.base.FilterList;
import ananas.axk2.core.XmppFilter;
import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.core.lang.BPNode;

public class CtrFilterList extends CtrObject {

	public FilterList target_FilterList() {
		return (FilterList) this.getTarget(true);
	}

	@Override
	protected boolean onAppendChild(BPNode node) {
		if (node instanceof BPElement) {
			Object target = ((BPElement) node).getTarget(true);
			if (target instanceof XmppFilter) {
				FilterList list = this.target_FilterList();
				list.append((XmppFilter) target);
			} else {
				return super.onAppendChild(node);
			}
		} else {
			return super.onAppendChild(node);
		}
		return true;
	}

	@Override
	protected boolean onSetAttribute(String uri, String localName, String value) {
		// TODO Auto-generated method stub
		return super.onSetAttribute(uri, localName, value);
	}

}
