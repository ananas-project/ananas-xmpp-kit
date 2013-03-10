package ananas.lib.axk.element.rosterver;

import ananas.lib.blueprint3.core.dom.AbstractElement;
import ananas.lib.blueprint3.core.dom.BPAttribute;

public class Ctrl_ver extends AbstractElement {

	public boolean set_attribute_id(BPAttribute attr) {
		return true;
	}

	public boolean append_child_optional(Ctrl_optional op) {
		return true;
	}
}
