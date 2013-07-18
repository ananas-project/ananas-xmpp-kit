package ananas.axk2.engine.test;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.engine.XEngine;
import ananas.axk2.engine.XEngineContext;
import ananas.axk2.engine.XEngineFactory;
import ananas.lib.util.logging.Logger;

public class TestAxkEngine {

	final static Logger log = Logger.Agent.getLogger();

	public static void main(String[] arg) {
		TestAxkEngine test = new TestAxkEngine();
		test.run();
	}

	private void run() {
		log.trace("start " + this);
		XEngineFactory factory = XEngineFactory.Agent.getDefault();
		XEngineContext context = new MyEngineContext();
		XEngine engine = factory.createEngine(context);
		engine.start();
	}

	static class MyEngineContext implements XEngineContext {

		@Override
		public XmppAccount getAccount() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
