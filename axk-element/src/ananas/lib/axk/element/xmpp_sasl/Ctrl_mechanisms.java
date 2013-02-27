package ananas.lib.axk.element.xmpp_sasl;

import ananas.lib.blueprint.core.dom.AbstractElement;
import ananas.lib.blueprint.core.dom.BPAttribute;

public class Ctrl_mechanisms extends AbstractElement {

	public boolean set_attribute_id(BPAttribute attr) {
		return false;
	}

	public boolean append_child_mechanism(Ctrl_mechanism child) {
		Xmpp_mechanism c = child.getTarget_mechanism();
		Xmpp_mechanisms p = this.getTarget_mechanisms();
		p.add(c);
		return true;
	}

	public Xmpp_mechanisms getTarget_mechanisms() {
		return (Xmpp_mechanisms) this.getTarget(true);
	}
}
