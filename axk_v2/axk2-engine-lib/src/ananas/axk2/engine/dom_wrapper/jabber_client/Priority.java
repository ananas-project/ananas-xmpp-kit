package ananas.axk2.engine.dom_wrapper.jabber_client;

import org.w3c.dom.Element;

import ananas.axk2.engine.dom_wrapper.DOMWrapperFactory;
import ananas.axk2.engine.dom_wrapper.DWDocument;
import ananas.axk2.engine.dom_wrapper.DWElement;

public class Priority extends JabberClientObject {

	private Priority(Element element, DWDocument owner) {
		super(element, owner);
	}

	public static DOMWrapperFactory getFactory() {
		return new DOMWrapperFactory() {
			@Override
			public DWElement wrapElement(Element element, DWDocument owner) {
				return new Priority(element, owner);
			}
		};
	}

}
