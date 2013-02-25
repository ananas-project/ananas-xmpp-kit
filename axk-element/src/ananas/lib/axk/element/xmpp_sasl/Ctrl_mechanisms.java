package ananas.lib.axk.element.xmpp_sasl;

import ananas.lib.blueprint.core.dom.AbstractElement;
import ananas.lib.blueprint.core.dom.BPAttribute;

public class Ctrl_mechanisms extends AbstractElement {

	public boolean set_attribute_id(BPAttribute attr) {
		return false;
	}

	public boolean append_child_mechanism(Ctrl_mechanism child) {
		return true;
	}
}
