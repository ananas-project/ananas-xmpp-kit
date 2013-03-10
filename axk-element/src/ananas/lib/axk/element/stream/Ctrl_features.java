package ananas.lib.axk.element.stream;

import ananas.lib.blueprint3.core.dom.AbstractElement;
import ananas.lib.blueprint3.core.dom.BPAttribute;
import ananas.lib.blueprint3.core.dom.BPElement;

public class Ctrl_features extends AbstractElement {

	private Xmpp_features m_target_features;

	public boolean set_attribute_to(BPAttribute attr) {
		return true;
	}

	public boolean append_child_(BPElement child) {
		Xmpp_features tar = this.getTarget_features();
		tar.addItem(child.getTarget(true));
		return true;
	}

	public Xmpp_features getTarget_features() {
		Xmpp_features rlt = this.m_target_features;
		if (rlt == null) {
			rlt = (Xmpp_features) this.getTarget(true);
			this.m_target_features = rlt;
		}
		return rlt;
	}

}
