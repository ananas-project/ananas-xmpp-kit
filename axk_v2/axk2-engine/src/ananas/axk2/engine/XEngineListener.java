package ananas.axk2.engine;

import org.w3c.dom.Element;

import ananas.axk2.core.XmppStatus;

public interface XEngineListener {

	void onStanza(XEngine engine, Element stanza);

	void onPhaseChanged(XEngine engine, XmppStatus oldPhase, XmppStatus newPhase);
}
