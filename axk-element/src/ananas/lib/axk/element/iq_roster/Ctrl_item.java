package ananas.lib.axk.element.iq_roster;

import ananas.lib.blueprint.core.dom.AbstractElement;
import ananas.lib.blueprint.core.dom.BPAttribute;

public class Ctrl_item extends AbstractElement {

	public boolean set_attribute_jid(BPAttribute attr) {
		this.target_item().setJID(attr.getValue());
		return true;
	}

	public boolean set_attribute_subscription(BPAttribute attr) {
		this.target_item().setSubscription(attr.getValue());
		return true;
	}

	public boolean set_attribute_name(BPAttribute attr) {
		this.target_item().setName(attr.getValue());
		return true;
	}

	public Xmpp_item target_item() {
		return (Xmpp_item) this.getTarget(true);
	}

}
