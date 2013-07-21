package impl.ananas.axk2.core.base.bp;

import impl.ananas.axk2.core.base.Filter;
import impl.ananas.axk2.core.base.FilterList;
import ananas.blueprint4.core.lang.BPNode;

public class CtrFilterList extends CtrObject {

	public FilterList target_FilterList() {
		return (FilterList) this.getTarget(true);
	}

	@Override
	protected boolean onAppendChild(BPNode node) {
		if (node instanceof CtrFilter) {
			Filter filter = ((CtrFilter) node).target_Filter();
			FilterList list = this.target_FilterList();
			list.append(filter);
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
