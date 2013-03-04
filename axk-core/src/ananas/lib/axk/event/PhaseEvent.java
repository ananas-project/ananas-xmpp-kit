package ananas.lib.axk.event;

import ananas.lib.axk.XmppStatus;

public interface PhaseEvent extends XmppClientEvent {

	XmppStatus getOldPhase();

	XmppStatus getNewPhase();

}
