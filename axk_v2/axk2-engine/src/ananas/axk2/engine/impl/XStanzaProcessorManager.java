package ananas.axk2.engine.impl;

import ananas.axk2.core.XmppStatus;

public interface XStanzaProcessorManager {

	XStanzaProcessor getCurrentProcessor();

	void setCurrentProcessor(XStanzaProcessor proc);

	XStanzaProcessor getProcessorForStatus(XmppStatus status);

}
