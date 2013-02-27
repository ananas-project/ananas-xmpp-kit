package ananas.lib.axk.element.xmpp_sasl;

import ananas.lib.blueprint.core.dom.AbstractElement;

public class Ctrl_sasl_error extends AbstractElement {

	@Override
	public Object createTarget() {
		String name = this.getType().getLocalName();
		return new Xmpp_sasl_error(name);
	}

}
