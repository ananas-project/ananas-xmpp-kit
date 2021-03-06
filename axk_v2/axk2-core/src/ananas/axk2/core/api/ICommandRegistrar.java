package ananas.axk2.core.api;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.command.XmppCommandFactory;

public interface ICommandRegistrar extends XmppAPI {

	XmppCommandFactory getFactory(Class<?> api);

	// void registerFactory(Class<?> api, String factoryClass);

	// void registerFactory(Class<?> api, XmppCommandFactory factory);

	void registerFactory(XmppCommandFactory factory);

}
