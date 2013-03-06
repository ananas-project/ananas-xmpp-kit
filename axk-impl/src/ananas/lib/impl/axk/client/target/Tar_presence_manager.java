package ananas.lib.impl.axk.client.target;

import ananas.lib.axk.XmppAddress;
import ananas.lib.axk.api.IExPresenceManager;
import ananas.lib.axk.element.jabber_client.Xmpp_presence;

public class Tar_presence_manager extends Tar_abstractClient implements
		IExPresenceManager {

	private boolean mIsAutoPresenceAfterBinding;

	@Override
	public void setMyPresence(Xmpp_presence presence) {
		// TODO Auto-generated method stub

	}

	@Override
	public Xmpp_presence getPresence(XmppAddress jid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAutoPresenceAfterBinding() {
		return this.mIsAutoPresenceAfterBinding;
	}

	@Override
	public void setAutoPresenceAfterBinding(boolean value) {
		this.mIsAutoPresenceAfterBinding = value;
	}

}
