package ananas.lib.axk.element.jabber_client;

import ananas.lib.blueprint.core.dom.AbstractElement;
import ananas.lib.blueprint.core.dom.BPAttribute;
import ananas.lib.blueprint.core.dom.BPElement;

public class Ctrl_Stanza extends AbstractElement {

	public Xmpp_Stanza target_stanza() {
		return (Xmpp_Stanza) this.getTarget(true);
	}

	public boolean set_attribute_id(BPAttribute attr) {
		return true;
	}

	public boolean set_attribute_from(BPAttribute attr) {
		return true;
	}

	public boolean set_attribute_to(BPAttribute attr) {
		return true;
	}

	public boolean set_attribute_type(BPAttribute attr) {
		return true;
	}

	public boolean set_attribute_lang(BPAttribute attr) {
		return true;
	}

	public boolean set_attribute_xml_lang(BPAttribute attr) {
		return true;
	}

	public boolean append_child_(Object child) {
		if (child instanceof BPElement) {
			BPElement childElement = (BPElement) child;
			this.target_stanza().addItem(childElement.getTarget(true));
			return true;
		} else {
			return false;
		}
	}

}
