package ananas.axk2.engine.api;

import ananas.axk2.core.XmppStatus;

public interface XStanzaProcessorManager {

	XStanzaProcessor getCurrentProcessor();

	void setCurrentProcessor(XStanzaProcessor proc);

	XStanzaProcessor getProcessorForStatus(XmppStatus status);

}
