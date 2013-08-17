package impl.ananas.axk2.ex.kit1;

import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppCommandListener;
import ananas.axk2.core.XmppCommandStatus;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.event.StanzaEvent;
import ananas.axk2.ex.kit1.contact.IContactModel;
import ananas.axk2.ex.kit1.contact.XmppResource;
import ananas.axk2.namespace.jabber_client.Iq;
import ananas.axk2.namespace.jabber_client.Message;
import ananas.axk2.namespace.jabber_client.Presence;
import ananas.lib.util.logging.Logger;

public class TPresenceManager extends TFilter {

	final static Logger log = Logger.Agent.getLogger();

	@Override
	public XmppEvent filter(XmppEvent event) {
		if (event == null) {
		} else if (event instanceof StanzaEvent) {
			this.__onStanzaEvent((StanzaEvent) event);
		}
		return event;
	}

	private IContactModel __getContactManager() {
		IContactModel cm = (IContactModel) this.getConnection().getAPI(
				IContactModel.class);
		return cm;
	}

	private void __onStanzaEvent(StanzaEvent event) {

		final Object stanza = event.getObject();
		if (stanza == null) {
			log.warn("null stanza : " + stanza);

		} else if (stanza instanceof Presence) {
			this.__onStanzaPresence(event, (Presence) stanza);

		} else if (stanza instanceof Message) {
		} else if (stanza instanceof Iq) {
		} else {
			log.warn("unknow stanza : " + stanza);
		}

	}

	private void __onStanzaPresence(StanzaEvent event, Presence presence) {

		StringBuilder sb = new StringBuilder();
		sb.append("[Presence");
		sb.append(" id:" + presence.getId());
		sb.append(" from:" + presence.getFrom());
		sb.append(" to:" + presence.getTo());
		sb.append(" type:" + presence.getType());
		sb.append(" xml:lang:" + presence.getXMLLang());
		sb.append("]");
		log.info(sb.toString());

		String from = presence.getFrom();

		IContactModel cm = this.__getContactManager();
		XmppResource res = cm.getResource(from, true);
		XmppAddress addr = res.getAddress();
		log.info("presence-addr=" + addr);

	}

	class MyCmdListener implements XmppCommandListener {

		@Override
		public void onStatusChanged(XmppCommandStatus oldStatus,
				XmppCommandStatus newStatus) {

			log.trace(this + ".status : " + newStatus);

		}
	}
}
