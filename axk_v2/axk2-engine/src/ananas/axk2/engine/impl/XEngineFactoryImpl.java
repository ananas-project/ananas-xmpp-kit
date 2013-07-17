package ananas.axk2.engine.impl;

import ananas.axk2.engine.XEngine;
import ananas.axk2.engine.XEngineContext;
import ananas.axk2.engine.XEngineFactory;

public class XEngineFactoryImpl implements XEngineFactory {

	@Override
	public XEngine createEngine(XEngineContext context) {
		return new XEngineImpl(context);
	}

}
