package ananas.lib.axk.plugin;

import java.util.List;

public interface PluginManager {

	void installPlugin(Plugin plugin);

	void uninstallPlugin(Plugin plugin);

	List<Plugin> listPlugin();

}
