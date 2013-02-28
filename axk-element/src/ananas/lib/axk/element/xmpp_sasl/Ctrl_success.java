package ananas.lib.axk.element.xmpp_sasl;

import ananas.lib.blueprint.core.dom.AbstractElement;

public class Ctrl_success extends AbstractElement {

	public Xmpp_success getTarget_success() {
		return (Xmpp_success) this.getTarget(true);
	}

}
