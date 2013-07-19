package ananas.axk2.engine.dom_wrapper;

import org.w3c.dom.Element;

public class DefaultElementWrapper extends AbstractElementWrapper {

	public DefaultElementWrapper(Element element, DWDocument owner) {
		super(element, owner);
	}

	private static final DOMWrapperFactory _factory = new DOMWrapperFactory() {
		@Override
		public DWElement wrapElement(Element element, DWDocument owner) {
			return new DefaultElementWrapper(element, owner);
		}
	};

	public static DOMWrapperFactory getFactory() {
		return _factory;
	}

}
