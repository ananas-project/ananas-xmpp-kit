package ananas.axk2.xmpphost;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppStatus;

public interface IXmppWanHost extends IXmppHost {

	void connect(IXmppHostListener listener);

	void disconnect(IXmppHostListener listener);

	void setAccount(XmppAccount account, IXmppHostListener listener);

	XmppAccount getAccount();

	XmppStatus getPhase();

	XmppStatus getStatus();

}
