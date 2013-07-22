package ananas.axk2.engine.dom_wrapper;

import org.w3c.dom.Element;

public interface DWDocument extends DWNode {

	DOMWrapperImplementation getImplementation();

	DWElement wrapElement(Element element);
}
