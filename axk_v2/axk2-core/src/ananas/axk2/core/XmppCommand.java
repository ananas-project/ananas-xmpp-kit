package ananas.axk2.core;

public interface XmppCommand {

	void onSendBy(XmppFilter filter);

	void setListener(XmppCommandListener listener);

	void setStatus(XmppCommandStatus status);

	XmppCommandStatus getStatus();

}
