package ananas.axk2.engine.impl;

import org.w3c.dom.Element;

public interface XStanzaProcessor {

	void onStanza(XEngineRuntimeContext erc, Element element);

}
