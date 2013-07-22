package ananas.axk2.engine;

import ananas.lib.util.SingletonLoader;

public interface XEngineFactory {

	XEngine createEngine(XEngineContext context);

	class Agent {

		public static XEngineFactory getDefault() {
			return (XEngineFactory) SingletonLoader.load(XEngineFactory.class);
		}
	}

}
