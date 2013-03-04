package ananas.lib.axk.event;

import ananas.lib.axk.XmppStatus;

public interface StatusEvent extends XmppClientEvent {

	XmppStatus getOldStatus();

	XmppStatus getNewStatus();

}
