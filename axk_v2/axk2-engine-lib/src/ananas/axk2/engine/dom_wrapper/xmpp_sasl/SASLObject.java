package ananas.axk2.engine.dom_wrapper.xmpp_sasl;

import org.w3c.dom.Element;

import ananas.axk2.engine.dom_wrapper.AbstractElementWrapper;
import ananas.axk2.engine.dom_wrapper.DWDocument;

class SASLObject extends AbstractElementWrapper {

	protected SASLObject(Element element, DWDocument owner) {
		super(element, owner);
	}

	/*
	public static DOMWrapperFactory getFactory() {
		return new DOMWrapperFactory() {
			@Override
			public DWElement wrapElement(Element element, DWDocument owner) {
				return new SASLObject(element, owner);
			}
		};
	}
	*/

}
