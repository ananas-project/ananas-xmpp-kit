package ananas.axk2.engine;

import ananas.axk2.core.XmppCommandStatus;

public interface XSendContext {

	void onStep(XmppCommandStatus step, Throwable err);

	int getPriority();

	String getContent();
}
