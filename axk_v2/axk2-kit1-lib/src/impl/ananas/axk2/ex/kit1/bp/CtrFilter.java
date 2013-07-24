package impl.ananas.axk2.ex.kit1.bp;

import ananas.axk2.core.XmppFilter;

public class CtrFilter extends CtrObject {

	public XmppFilter target_Filter() {
		return (XmppFilter) this.getTarget(true);
	}

}
