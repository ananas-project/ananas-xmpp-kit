package ananas.axk2.engine.dom_wrapper.streams;

import ananas.axk2.engine.dom_wrapper.DOMWrapperFactory;
import ananas.axk2.engine.dom_wrapper.DOMWrapperFactoryLoader;
import ananas.axk2.engine.dom_wrapper.DOMWrapperImplementation;
import ananas.axk2.engine.dom_wrapper.DOMWrapperRegistrar;

public class TheLoader implements DOMWrapperFactoryLoader {

	final static String uri = "http://etherx.jabber.org/streams";

	@Override
	public void load(DOMWrapperImplementation impl) {

		MyHelper hp = new MyHelper(impl.getRegistrar());

		hp.reg("features", Features.getFactory());
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
