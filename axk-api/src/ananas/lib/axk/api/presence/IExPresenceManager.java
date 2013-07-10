package ananas.lib.axk.api.presence;

import ananas.lib.axk.XmppAddress;
import ananas.lib.axk.XmppClientExAPI;
import ananas.lib.axk.constant.XmppShow;

public interface IExPresenceManager extends XmppClientExAPI {

	// my presence

	void setMyPresenceShow(XmppShow show);

	XmppShow getMyPresenceShow();

	// attributes

	boolean isAutoPresenceAfterBinding();

	void setAutoPresenceAfterBinding(boolean value);

	// others

	void clear();

	IPresenceSet getPresenceSet(XmppAddress jid);

	IPresenceRes getPresenceRes(XmppAddress jid);

}
