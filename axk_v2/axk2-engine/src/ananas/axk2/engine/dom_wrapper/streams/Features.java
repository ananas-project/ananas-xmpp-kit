package ananas.axk2.engine.dom_wrapper.streams;

import org.w3c.dom.Element;

import ananas.axk2.engine.dom_wrapper.AbstractElementWrapper;
import ananas.axk2.engine.dom_wrapper.DOMWrapperFactory;
import ananas.axk2.engine.dom_wrapper.DWDocument;
import ananas.axk2.engine.dom_wrapper.DWElement;

public class Features extends AbstractElementWrapper {

	public Features(Element element, DWDocument owner) {
		super(element, owner);
	}

	public static DOMWrapperFactory getFactory() {
		return new DOMWrapperFactory() {
			@Override
			public DWElement wrapElement(Element element, DWDocument owner) {
				return new Features(element, owner);
			}
		};
	}

}
