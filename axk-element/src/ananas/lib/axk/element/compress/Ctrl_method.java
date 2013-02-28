package ananas.lib.axk.element.compress;

import ananas.lib.blueprint.core.dom.AbstractElement;
import ananas.lib.blueprint.core.dom.BPAttribute;
import ananas.lib.blueprint.core.dom.BPText;

public class Ctrl_method extends AbstractElement {

	public boolean set_attribute_id(BPAttribute attr) {
		return true;
	}

	public boolean onAppendText(BPText text) {
		Xmpp_method mtd = this.getTarget_method();
		mtd.setName(text.getData());
		return true;
	}

	private Xmpp_method getTarget_method() {
		return (Xmpp_method) this.getTarget(true);
	}
}
