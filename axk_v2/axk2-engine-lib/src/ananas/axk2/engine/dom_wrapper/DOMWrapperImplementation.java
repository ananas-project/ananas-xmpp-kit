package ananas.axk2.engine.dom_wrapper;

import org.w3c.dom.Document;

public interface DOMWrapperImplementation {

	DOMWrapperRegistrar getRegistrar();

	DWDocument wrapDocument(Document doc);

}
