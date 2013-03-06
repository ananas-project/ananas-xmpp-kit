package ananas.lib.axk.element;

import ananas.lib.axk.DefaultXmppAddress;
import ananas.lib.axk.XmppAddress;
import ananas.lib.blueprint.core.dom.AbstractElement;

public abstract class AbstractXmppObject extends AbstractElement {

	protected XmppAddress getXmppAddress(String jid, boolean noCache) {
		if (noCache) {
			return new DefaultXmppAddress(jid);
		}
		// TODO add cache support here
		return new DefaultXmppAddress(jid);
	}

	protected XmppAddress getXmppAddress(String jid) {
		return this.getXmppAddress(jid, false);
	}

}
