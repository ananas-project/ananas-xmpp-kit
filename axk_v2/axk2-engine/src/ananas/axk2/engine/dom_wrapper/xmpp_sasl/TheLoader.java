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
		hp.reg("abort", Abort.getFactory());
		hp.reg("auth", Auth.getFactory());
		hp.reg("challenge", Challenge.getFactory());
		hp.reg("response", Response.getFactory());
		hp.reg("success", Success.getFactory());
		hp.reg("failure", Failure.getFactory());
		hp.reg("text", Text.getFactory());

		hp.reg("empty", Empty.getFactory());

		hp.reg("aborted", Empty.getFactory());
		hp.reg("account-disabled", Empty.getFactory());
		hp.reg("credentials-expired", Empty.getFactory());
		hp.reg("encryption-required", Empty.getFactory());
		hp.reg("incorrect-encoding", Empty.getFactory());
		hp.reg("invalid-authzid", Empty.getFactory());
		hp.reg("invalid-mechanism", Empty.getFactory());
		hp.reg("malformed-request", Empty.getFactory());
		hp.reg("mechanism-too-weak", Empty.getFactory());
		hp.reg("not-authorized", Empty.getFactory());
		hp.reg("temporary-auth-failure", Empty.getFactory());
		hp.reg("transition-needed", Empty.getFactory());

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
