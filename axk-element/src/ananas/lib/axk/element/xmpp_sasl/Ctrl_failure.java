package ananas.lib.axk.element.xmpp_sasl;

import ananas.lib.blueprint.core.dom.AbstractElement;

public class Ctrl_failure extends AbstractElement {

	public boolean append_child_(Ctrl_sasl_error err) {
		Xmpp_sasl_error error = (Xmpp_sasl_error) err.getTarget(true);
		Xmpp_failure failure = this.getTarget_failure();
		failure.setError(error);
		return true;
	}

	public Xmpp_failure getTarget_failure() {
		return (Xmpp_failure) this.getTarget(true);
	}

}
