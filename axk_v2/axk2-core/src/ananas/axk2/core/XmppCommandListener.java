package ananas.axk2.core;

public interface XmppCommandListener {

	void onStatusChanged(XmppCommandStatus oldStatus,
			XmppCommandStatus newStatus);

}
