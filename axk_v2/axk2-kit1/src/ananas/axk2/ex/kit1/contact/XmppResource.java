package ananas.axk2.ex.kit1.contact;

import ananas.axk2.core.XmppAddress;

public interface XmppResource extends AttributeSet {

	XmppContact getOwnerContact();

	XmppAddress getAddress();
}
