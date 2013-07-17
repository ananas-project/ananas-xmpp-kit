package ananas.axk2.engine.test;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.engine.XEngine;
import ananas.axk2.engine.XEngineContext;
import ananas.axk2.engine.XEngineFactory;

public class TestAxkEngine {

	public static void main(String[] arg) {

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
