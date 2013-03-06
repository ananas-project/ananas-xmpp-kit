package ananas.lib.axk.element.jabber_client;

import ananas.lib.axk.XmppAddress;
import ananas.lib.axk.element.AbstractXmppObject;
import ananas.lib.blueprint.core.dom.BPAttribute;
import ananas.lib.blueprint.core.dom.BPElement;

public class Ctrl_Stanza extends AbstractXmppObject {

	public Xmpp_Stanza target_stanza() {
		return (Xmpp_Stanza) this.getTarget(true);
	}

	public boolean set_attribute_id(BPAttribute attr) {
		this.target_stanza().setId(attr.getValue());
		return true;
	}

	public boolean set_attribute_from(BPAttribute attr) {
		XmppAddress addr = this.getXmppAddress(attr.getValue(), false);
		this.target_stanza().setFrom(addr);
		return true;
	}

	public boolean set_attribute_to(BPAttribute attr) {
		XmppAddress addr = this.getXmppAddress(attr.getValue(), false);
		this.target_stanza().setTo(addr);
		return true;
	}

	public boolean set_attribute_type(BPAttribute attr) {
		this.target_stanza().setType(attr.getValue());
		return true;
	}

	public boolean set_attribute_lang(BPAttribute attr) {
		this.target_stanza().setXmlLang(attr.getValue());
		return true;
	}

	public boolean set_attribute_xml_lang(BPAttribute attr) {
		this.target_stanza().setXmlLang(attr.getValue());
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
