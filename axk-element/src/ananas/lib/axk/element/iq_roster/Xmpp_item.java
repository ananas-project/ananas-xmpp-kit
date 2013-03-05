package ananas.lib.axk.element.iq_roster;

public class Xmpp_item {

	private String mSubscription;
	private String mName;
	private String mJID;

	public void setJID(String value) {
		this.mJID = value;
	}

	public void setName(String value) {
		this.mName = value;
	}

	public void setSubscription(String value) {
		this.mSubscription = value;
	}

	public String getSubscription() {
		return mSubscription;
	}

	public String getName() {
		return mName;
	}

	public String getJID() {
		return mJID;
	}

}
