package ananas.lib.axk.engine.impl;

import ananas.lib.axk.engine.XEngine;
import ananas.lib.axk.engine.XEngineFactory;

public class TheDefaultXConnectionFactory implements XEngineFactory {

	@Override
	public XEngine createEngine() {
		return new XEngineImpl();
	}

}
