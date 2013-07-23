package ananas.axk2.core.event;

import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppConnection;

public interface BindEventFactory extends XmppEventFactory {

	BindEvent create(XmppConnection conn, XmppAddress bind);
}
