package ananas.axk2.core.event;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppStatus;

public interface PhaseEventFactory extends XmppEventFactory {

	PhaseEvent create(XmppConnection conn, XmppStatus theOld, XmppStatus theNew);
}
