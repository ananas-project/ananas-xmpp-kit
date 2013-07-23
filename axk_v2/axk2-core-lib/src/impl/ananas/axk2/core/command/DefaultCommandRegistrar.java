package impl.ananas.axk2.core.command;

import java.util.HashMap;
import java.util.Map;

import ananas.axk2.core.api.ICommandRegistrar;
import ananas.axk2.core.command.XmppCommandFactory;

public class DefaultCommandRegistrar implements ICommandRegistrar {

	private final Map<String, Class<?>> _class_table;

	public DefaultCommandRegistrar() {
		this._class_table = new HashMap<String, Class<?>>();
	}

	@Override
	public XmppCommandFactory getFactory(Class<?> api) {
		return null;
	}

	@Override
	public void registerFactory(Class<?> api, String factoryClass) {
		try {
			Class<?> impl = Class.forName(factoryClass);
			this._class_table.put(api.getName(), impl);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registerFactory(Class<?> api, XmppCommandFactory factory) {
		Class<?> impl = factory.getClass();
		this._class_table.put(api.getName(), impl);
	}

}
