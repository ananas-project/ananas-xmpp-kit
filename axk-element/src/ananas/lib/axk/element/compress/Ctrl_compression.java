package ananas.lib.axk.element.compress;

import ananas.lib.blueprint3.core.dom.AbstractElement;
import ananas.lib.blueprint3.core.dom.BPAttribute;

public class Ctrl_compression extends AbstractElement {

	public boolean set_attribute_id(BPAttribute attr) {
		return true;
	}

	public boolean append_child_method(Ctrl_method method) {
		return true;
	}
}
