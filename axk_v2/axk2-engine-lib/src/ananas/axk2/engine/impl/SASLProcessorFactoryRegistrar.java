package ananas.axk2.engine.impl;

public interface SASLProcessorFactoryRegistrar {

	SASLProcessorFactory getFactory(String keyName);

	void registerFactory(String keyName, SASLProcessorFactory factory);

	void registerFactory(String keyName, Class<?> factoryClass);

}
