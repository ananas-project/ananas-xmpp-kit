package ananas.lib.axk.element.stream;

import ananas.lib.blueprint.core.dom.AbstractElement;
import ananas.lib.blueprint.core.dom.BPAttribute;
import ananas.lib.blueprint.core.dom.BPElement;

public class Ctrl_features extends AbstractElement {

	public boolean set_attribute_to(BPAttribute attr) {
		return true;
	}

	public boolean append_child_(BPElement child) {

		BPElement element = (BPElement) child;
		System.out.println(this + " <<[append]<< " + element);

		return true;

	}
}
