package ananas.lib.axk.element.iq_roster;

import ananas.lib.blueprint3.core.dom.AbstractElement;
import ananas.lib.blueprint3.core.dom.BPText;

public class Ctrl_group extends AbstractElement {

	public Xmpp_group target_group() {
		return (Xmpp_group) this.getTarget(true);
	}

	public boolean append_text(BPText text) {
		String data = text.getData();
		this.target_group().setName(data);
		return true;
	}

}
