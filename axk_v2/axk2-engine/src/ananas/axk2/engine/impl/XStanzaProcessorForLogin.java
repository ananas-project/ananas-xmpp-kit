package ananas.axk2.engine.impl;

import org.w3c.dom.Element;

import ananas.axk2.engine.dom_wrapper.DOMWrapperImplementation;
import ananas.axk2.engine.dom_wrapper.DWDocument;
import ananas.axk2.engine.dom_wrapper.DWElement;
import ananas.lib.util.logging.Logger;

public class XStanzaProcessorForLogin implements XStanzaProcessor {

	final static Logger log = Logger.Agent.getLogger();

	@Override
	public void onStanza(XEngineRuntimeContext erc, Element element) {

		DOMWrapperImplementation impl = erc.getSubConnection().getParent()
				.getDOMWrapperImplementation();
		DWDocument doc = impl.wrapDocument(element.getOwnerDocument());
		DWElement wEle = doc.wrapElement(element);

		// log.warn("unknow object : " + wEle);
		wEle.printTree(System.out, "dom-tree:");

	}
}
