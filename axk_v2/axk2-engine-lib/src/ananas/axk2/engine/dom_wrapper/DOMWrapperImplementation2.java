package ananas.axk2.engine.dom_wrapper;

import org.w3c.dom.Document;

public class DOMWrapperImplementation2 implements DOMWrapperImplementation {

	private final DOMWrapperRegistrar _reg;

	public DOMWrapperImplementation2() {
		this._reg = new DOMWrapperRegistrarImpl();
	}

	@Override
	public DOMWrapperRegistrar getRegistrar() {
		return this._reg;
	}

	@Override
	public DWDocument wrapDocument(Document doc) {
		return new DWDocumentImpl(doc, this);
	}

}
