package ananas.axk2.engine.dom_wrapper.jabber_client;

import ananas.axk2.engine.dom_wrapper.DOMWrapperFactory;
import ananas.axk2.engine.dom_wrapper.DOMWrapperFactoryLoader;
import ananas.axk2.engine.dom_wrapper.DOMWrapperImplementation;
import ananas.axk2.engine.dom_wrapper.DOMWrapperRegistrar;

public class TheLoader implements DOMWrapperFactoryLoader {

	final static String uri = "jabber:client";

	@Override
	public void load(DOMWrapperImplementation impl) {

		MyHelper hp = new MyHelper(impl.getRegistrar());

		hp.reg("body", Body.getFactory());
		hp.reg("subject", Subject.getFactory());
		hp.reg("thread", Thread.getFactory());
		hp.reg("show", Show.getFactory());
		hp.reg("status", Status.getFactory());
		hp.reg("priority", Priority.getFactory());
		hp.reg("error", Error.getFactory());

		hp.reg("iq", Iq.getFactory());
		hp.reg("message", Message.getFactory());
		hp.reg("presence", Presence.getFactory());

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
