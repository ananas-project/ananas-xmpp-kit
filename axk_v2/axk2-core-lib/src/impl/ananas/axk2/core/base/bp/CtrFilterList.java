package impl.ananas.axk2.core.base.bp;

import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.XmppFilterList;
import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.core.lang.BPNode;

public class CtrFilterList extends CtrObject {

	public XmppFilterList target_FilterList() {
		return (XmppFilterList) this.getTarget(true);
	}

	@Override
	protected boolean onAppendChild(BPNode node) {
		if (node instanceof BPElement) {
			Object target = ((BPElement) node).getTarget(true);
			if (target instanceof XmppFilter) {
				XmppFilterList list = this.target_FilterList();
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
