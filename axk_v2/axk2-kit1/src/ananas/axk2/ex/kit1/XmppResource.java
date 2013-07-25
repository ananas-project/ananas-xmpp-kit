package ananas.axk2.ex.kit1;

import ananas.axk2.core.XmppAddress;

public interface XmppResource {

	XmppContact getOwnerContact();

	XmppAddress getAddress();
}
