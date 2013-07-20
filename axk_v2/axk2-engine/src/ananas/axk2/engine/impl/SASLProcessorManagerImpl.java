package ananas.axk2.engine.impl;

public class SASLProcessorManagerImpl implements SASLProcessorManager {

	private SASLProcessor _current_proc;
	private SASLProcessorFactoryRegistrar _reg = new SASLProcessorFactoryRegistrarImpl();

	@Override
	public void setCurrent(SASLProcessor proc) {
		this._current_proc = proc;
	}

	@Override
	public SASLProcessor getCurrent() {
		return this._current_proc;
	}

	@Override
	public SASLProcessor newProcessor(String keyName) {
		SASLProcessorFactory fact = this.getSASLProcessorFactoryRegistrar()
				.getFactory(keyName);
		if (fact != null) {
			return fact.newProcessor();
		} else {
			return null;
		}
	}

	@Override
	public SASLProcessorFactoryRegistrar getSASLProcessorFactoryRegistrar() {
		return this._reg;
	}

}
