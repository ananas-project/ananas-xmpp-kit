package ananas.axk2.core.event;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;

public interface XmppConnectionEvent extends XmppEvent {

	XmppConnection getConnection();

}
