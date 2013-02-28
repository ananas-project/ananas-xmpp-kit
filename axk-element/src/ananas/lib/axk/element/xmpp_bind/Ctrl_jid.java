package ananas.lib.axk.element.xmpp_bind;

import ananas.lib.blueprint.core.dom.AbstractElement;
import ananas.lib.blueprint.core.dom.BPText;

public class Ctrl_jid extends AbstractElement {

	public boolean append_text(BPText text) {
		this.target_jid().setText(text.getData());
		return true;
	}

	public Xmpp_jid target_jid() {
		return (Xmpp_jid) this.getTarget(true);
	}

}
