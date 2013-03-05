package ananas.lib.axk.api;

import ananas.lib.axk.XmppClientExAPI;
import ananas.lib.axk.element.iq_roster.Xmpp_query;

public interface IExRosterManager extends XmppClientExAPI {

	void pullRoster(boolean isLazy);

	Xmpp_query getRoster();

	boolean isAutoPullAfterBinding();

	void setAutoPullAfterBinding(boolean value);
}
