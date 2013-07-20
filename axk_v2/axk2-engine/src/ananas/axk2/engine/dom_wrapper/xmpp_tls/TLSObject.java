package ananas.axk2.engine.dom_wrapper.xmpp_tls;

import org.w3c.dom.Element;

import ananas.axk2.engine.dom_wrapper.AbstractElementWrapper;
import ananas.axk2.engine.dom_wrapper.DOMWrapperFactory;
import ananas.axk2.engine.dom_wrapper.DWDocument;
import ananas.axk2.engine.dom_wrapper.DWElement;

class TLSObject extends AbstractElementWrapper {

	protected TLSObject(Element element, DWDocument owner) {
		super(element, owner);
	}

	public static DOMWrapperFactory getFactory() {
		return new DOMWrapperFactory() {
			@Override
			public DWElement wrapElement(Element element, DWDocument owner) {
				return new TLSObject(element, owner);
			}
		};
	}

}
