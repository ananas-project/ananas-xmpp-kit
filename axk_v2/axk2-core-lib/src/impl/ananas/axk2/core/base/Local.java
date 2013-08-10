package impl.ananas.axk2.core.base;

import impl.ananas.axk2.core.command.DefaultCommandRegistrar;
import impl.ananas.axk2.core.event.DefaultEventRegistrar;
import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.api.ICommandRegistrar;
import ananas.axk2.core.api.IEventRegistrar;

public class Local extends Filter {

	private final IEventRegistrar _event_reg = new DefaultEventRegistrar();
	private final ICommandRegistrar _cmd_reg = new DefaultCommandRegistrar();

	@Override
	public XmppAPI getAPI(Class<?> apiClass) {
		if (apiClass.equals(IEventRegistrar.class)) {
			return this._event_reg;
		} else if (apiClass.equals(ICommandRegistrar.class)) {
			return this._cmd_reg;
		} else {
			return super.getAPI(apiClass);
		}
	}

}
