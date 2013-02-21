package ananas.lib.axk.api;

import ananas.lib.axk.XmppClientExAPI;
import ananas.lib.axk.XmppPhase;

public interface IExConnection extends XmppClientExAPI {

	void setStatus(XmppPhase phase);

	XmppPhase getStatus();

	XmppPhase getPhase();

}
