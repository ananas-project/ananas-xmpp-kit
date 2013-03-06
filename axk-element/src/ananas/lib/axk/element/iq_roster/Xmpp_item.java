package ananas.lib.axk.element.iq_roster;

import ananas.lib.axk.XmppAddress;
import ananas.lib.axk.XmppSubscription;

public class Xmpp_item {

	private XmppSubscription mSubscription;
	private String mName;
	private XmppAddress mJID;

	public void setJID(XmppAddress value) {
		this.mJID = value;
	}

	public void setName(String value) {
		this.mName = value;
	}

	public void setSubscription(XmppSubscription value) {
		this.mSubscription = value;
	}

	public XmppSubscription getSubscription() {
		return mSubscription;
	}

	public String getName() {
		return mName;
	}

	public XmppAddress getJID() {
		return mJID;
	}

}
