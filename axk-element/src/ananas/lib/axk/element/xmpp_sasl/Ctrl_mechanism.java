package ananas.lib.axk.element.xmpp_sasl;

import ananas.lib.blueprint.core.dom.AbstractElement;
import ananas.lib.blueprint.core.dom.BPAttribute;
import ananas.lib.blueprint.core.dom.BPText;

public class Ctrl_mechanism extends AbstractElement {

	public boolean set_attribute_id(BPAttribute attr) {
		return false;
	}

	public Xmpp_mechanism getTarget_mechanism() {
		return (Xmpp_mechanism) this.getTarget(true);
	}

	@Override
	public boolean onAppendText(BPText text) {
		String name = text.getData();
		this.getTarget_mechanism().setName(name);
		return true;
	}

}
