package ananas.lib.axk.api;

import ananas.lib.axk.XmppAddress;
import ananas.lib.axk.XmppClientExAPI;
import ananas.lib.axk.element.jabber_client.Xmpp_presence;

public interface IExPresenceManager extends XmppClientExAPI {

	/**
	 * @param presence
	 *            a presence stanza
	 * */
	void setMyPresence(String presence);

	/**
	 * @return a presence stanza
	 * */

	String getMyPresence();

	void sendMyPresence();

	void sendMyPresence(String presence);

	Xmpp_presence getPresence(XmppAddress jid);

	boolean isAutoPresenceAfterBinding();

	void setAutoPresenceAfterBinding(boolean value);

}
