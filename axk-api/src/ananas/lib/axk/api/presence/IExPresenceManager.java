package ananas.lib.axk.api.presence;

import ananas.lib.axk.XmppClientExAPI;

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

	// Xmpp_presence getPresence(XmppAddress jid);

	boolean isAutoPresenceAfterBinding();

	void setAutoPresenceAfterBinding(boolean value);

}
