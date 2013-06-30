package ananas.lib.axk.event;

import ananas.lib.axk.XmppClient;
import ananas.lib.axk.stanza.StanzaContext;
import ananas.lib.axk.stanza.StanzaContextRef;

public class StanzaEvent extends DefaultDataEvent implements StanzaContextRef {

	private StanzaContext m_target;

	public StanzaEvent(XmppClient client, Object data) {
		super(client, data);
	}

	@Override
	public StanzaContext getTarget() {
		return this.m_target;
	}

	@Override
	public void setTarget(StanzaContext target) {
		this.m_target = target;
	}

}
