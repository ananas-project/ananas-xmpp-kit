package ananas.axk2.core.api;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.event.XmppEventFactory;

public interface IEventRegistrar extends XmppAPI {

	XmppEventFactory getFactory(Class<?> factoryClass);

}
