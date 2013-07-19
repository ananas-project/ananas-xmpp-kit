package ananas.axk2.engine.dom_wrapper;

import org.w3c.dom.Element;

public interface DOMWrapperRegistrar {

	DOMWrapperFactory getFactory(String uri, String localName);

	DOMWrapperFactory getFactory(Element element);

	void registerFactory(String uri, String localName, DOMWrapperFactory factory);

	// void registerFactory(String uri, String localName,
	// Class<? extends DOMWrapperFactory> factory);

}
