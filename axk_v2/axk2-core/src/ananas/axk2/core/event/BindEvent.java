package ananas.axk2.core.event;

import ananas.axk2.core.XmppAddress;

public interface BindEvent extends XmppConnectionEvent {

	XmppAddress getBind();

}
