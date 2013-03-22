package ananas.lib.axk.element.compress;

import ananas.lib.blueprint3.core.dom.AbstractElement;
import ananas.lib.blueprint3.core.dom.BPAttribute;
import ananas.lib.blueprint3.core.dom.BPText;

public class Ctrl_method extends AbstractElement {

	public boolean set_attribute_id(BPAttribute attr) {
		return true;
	}

	public boolean append_text(BPText text) {
		Xmpp_method mtd = this.getTarget_method();
		mtd.setName(text.getData());
		return true;
	}

	private Xmpp_method getTarget_method() {
		return (Xmpp_method) this.getTarget(true);
	}
}
