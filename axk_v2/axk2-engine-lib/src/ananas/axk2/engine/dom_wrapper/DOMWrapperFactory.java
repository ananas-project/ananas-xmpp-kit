package ananas.axk2.engine.dom_wrapper;

import org.w3c.dom.Element;

public interface DOMWrapperFactory {

	DWElement wrapElement(Element element, DWDocument owner);
}
