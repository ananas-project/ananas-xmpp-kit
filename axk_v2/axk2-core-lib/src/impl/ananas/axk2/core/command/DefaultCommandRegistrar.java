package impl.ananas.axk2.core.command;

import java.util.HashMap;
import java.util.Map;

import ananas.axk2.core.api.ICommandRegistrar;
import ananas.axk2.core.command.XmppCommandFactory;

public class DefaultCommandRegistrar implements ICommandRegistrar {

	private final Map<Class<?>, XmppCommandFactory> _fact_table;

	public DefaultCommandRegistrar() {
		this._fact_table = new HashMap<Class<?>, XmppCommandFactory>();
		this.init();
	}

	private void init() {
		this.registerFactory(new TheStanzaCommandFactory());
	}

	@Override
	public XmppCommandFactory getFactory(Class<?> api) {
		return this._fact_table.get(api);
	}

	@Override
	public void registerFactory(XmppCommandFactory factory) {
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
