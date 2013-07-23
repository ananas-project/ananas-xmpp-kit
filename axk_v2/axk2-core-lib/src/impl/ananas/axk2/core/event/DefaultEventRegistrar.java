package impl.ananas.axk2.core.event;

import java.util.HashMap;
import java.util.Map;

import ananas.axk2.core.api.IEventRegistrar;
import ananas.axk2.core.event.XmppEventFactory;

public class DefaultEventRegistrar implements IEventRegistrar {

	private final Map<Class<?>, XmppEventFactory> _fact_table;

	public DefaultEventRegistrar() {
		this._fact_table = new HashMap<Class<?>, XmppEventFactory>();
		this.init();
	}

	private void init() {
		this.registerFactory(new ThePhaseEventFactory());
		this.registerFactory(new TheBindEventFactory());
	}

	@Override
	public XmppEventFactory getFactory(Class<?> factoryClass) {
		return this._fact_table.get(factoryClass);
	}

	@Override
	public void registerFactory(XmppEventFactory factory) {
		Class<?> cls = factory.getClass();
		for (; cls != null; cls = cls.getSuperclass()) {
			Class<?>[] apis = cls.getInterfaces();
			for (Class<?> api : apis) {
				this._fact_table.put(api, factory);
				// System.err.println(this + ".regEventFactory:" + factory
				// + " <- " + api);
			}
		}
	}

}
