package ananas.axk2.engine.api;

import org.w3c.dom.Element;

public interface XStanzaProcessor {

	void onStanza(XEngineRuntimeContext erc, Element element);

}
