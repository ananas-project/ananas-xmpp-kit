package impl.ananas.axk2.core.base.bp;

import impl.ananas.axk2.core.base.Case;
import ananas.axk2.core.XmppFilter;
import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.core.lang.BPNode;

public class CtrCase extends CtrObject {

	private String _case_ns;

	public Case target_case() {
		return (Case) this.getTarget(true);
	}

	@Override
	public Object createTarget() {
		String ns = this._case_ns;
		if (ns == null) {
			ns = "";
		}
		return new Case(ns);
	}

	@Override
	protected boolean onSetAttribute(String uri, String localName, String value) {
		if ("namespace".equals(localName)) {
			this._case_ns = value;
			return true;
		} else {
			return super.onSetAttribute(uri, localName, value);
		}
	}

	@Override
	protected boolean onAppendChild(BPNode node) {
		if (node instanceof BPElement) {
			Object ch_tar = ((BPElement) node).getTarget(true);
			if (ch_tar instanceof XmppFilter) {
				Case pt_tar = this.target_case();
				pt_tar.setFilter((XmppFilter) ch_tar);
				return true;
			}
		}
		return super.onAppendChild(node);
	}

}
