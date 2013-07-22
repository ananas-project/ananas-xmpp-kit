package ananas.axk2.core.event;

import ananas.axk2.core.XmppStatus;

public interface PhaseEvent extends XmppConnectionEvent {

	XmppStatus getNewPhase();

	XmppStatus getOldPhase();

}
