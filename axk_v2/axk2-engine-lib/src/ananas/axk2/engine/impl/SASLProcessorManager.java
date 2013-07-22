package ananas.axk2.engine.impl;

public interface SASLProcessorManager {

	void setCurrent(SASLProcessor proc);

	SASLProcessor getCurrent();

	SASLProcessor newProcessor(String keyName);

	SASLProcessorFactoryRegistrar getSASLProcessorFactoryRegistrar();
}
