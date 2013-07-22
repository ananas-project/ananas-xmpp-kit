package ananas.axk2.engine.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import ananas.lib.util.ClassPropertiesLoader;
import ananas.lib.util.logging.Logger;

public class SASLProcessorFactoryRegistrarImpl implements
		SASLProcessorFactoryRegistrar {

	final static Logger log = Logger.Agent.getLogger();

	private final Map<String, SASLProcessorFactory> _factory_table;
	private Map<String, Class<?>> _class_table;

	public SASLProcessorFactoryRegistrarImpl() {
		this._factory_table = new HashMap<String, SASLProcessorFactory>();
	}

	@Override
	public SASLProcessorFactory getFactory(String keyName) {
		SASLProcessorFactory fact = this._factory_table.get(keyName);
		if (fact == null) {
			Map<String, Class<?>> cmap = this.__getClassMap();
			Class<?> cls = cmap.get(keyName);
			if (cls != null) {
				try {
					fact = (SASLProcessorFactory) cls.newInstance();
					this._factory_table.put(keyName, fact);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		return fact;
	}

	@Override
	public void registerFactory(String keyName, SASLProcessorFactory factory) {
		this._factory_table.put(keyName, factory);
	}

	@Override
	public void registerFactory(String keyName, Class<?> factoryClass) {
		Map<String, Class<?>> cmap = this.__getClassMap();
		cmap.put(keyName, factoryClass);
	}

	private Map<String, Class<?>> __getClassMap() {
		Map<String, Class<?>> map = this._class_table;
		if (map == null) {
			map = new HashMap<String, Class<?>>();
			ClassPropertiesLoader ldr = new ClassPropertiesLoader(this);
			Properties prop = ldr.load();
			String prefix = "SASLProcessorFactory@";
			Set<Object> keys = prop.keySet();
			for (Object k : keys) {
				String key = k.toString();
				String value = prop.getProperty(key);
				if (key.startsWith(prefix)) {
					try {
						String kname = value;
						String clsName = key.substring(prefix.length());
						Class<?> cls = Class.forName(clsName);
						map.put(kname, cls);
					} catch (Exception e) {
						log.error(e);
					}
				}
			}
		}
		return map;
	}

}
