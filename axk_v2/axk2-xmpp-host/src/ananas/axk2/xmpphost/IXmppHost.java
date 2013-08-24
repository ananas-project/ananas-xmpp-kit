package ananas.axk2.xmpphost;

import ananas.axk2.core.XmppAPI;

public interface IXmppHost extends XmppAPI {

	void pull(IXmppHostListener listener);

}
