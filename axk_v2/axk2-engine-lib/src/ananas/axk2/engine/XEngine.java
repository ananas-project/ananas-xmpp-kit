package ananas.axk2.engine;

import ananas.axk2.core.XmppStatus;

public interface XEngine {

	void connect();

	void disconnect();

	XEngineContext getContext();

	XmppStatus getStatus();

	XmppStatus getPhase();

	void send(XSendContext context);

}
