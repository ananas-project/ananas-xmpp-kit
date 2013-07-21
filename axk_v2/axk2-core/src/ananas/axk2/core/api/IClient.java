package ananas.axk2.core.api;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppStatus;

public interface IClient extends XmppAPI {

	void connect();

	void disconnect();

	XmppStatus getStatus();

	XmppStatus getPhase();

}
