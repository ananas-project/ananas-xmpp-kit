package ananas.axk2.engine.dom_wrapper.xmpp_sasl;

import ananas.axk2.engine.dom_wrapper.DOMWrapperFactory;
import ananas.axk2.engine.dom_wrapper.DOMWrapperFactoryLoader;
import ananas.axk2.engine.dom_wrapper.DOMWrapperImplementation;
import ananas.axk2.engine.dom_wrapper.DOMWrapperRegistrar;

public class TheLoader implements DOMWrapperFactoryLoader {

	final static String uri = "urn:ietf:params:xml:ns:xmpp-sasl";

	@Override
	public void load(DOMWrapperImplementation impl) {

		MyHelper hp = new MyHelper(impl.getRegistrar());

		hp.reg("mechanisms", Mechanisms.getFactory());
		hp.reg("mechanism", Mechanism.getFactory());
	}

	class MyHelper {

		private final DOMWrapperRegistrar _reg;

		public MyHelper(DOMWrapperRegistrar registrar) {
			this._reg = registrar;
		}

		public void reg(String localName, DOMWrapperFactory factory) {
			this._reg.registerFactory(uri, localName, factory);
		}
	}

}
