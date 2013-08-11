package impl.ananas.axk2.core.base;

import impl.ananas.axk2.core.command.DefaultCommandRegistrar;
import impl.ananas.axk2.core.event.DefaultEventRegistrar;
import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.api.ICommandRegistrar;
import ananas.axk2.core.api.IEventRegistrar;

public class Local extends Filter {

	private final IEventRegistrar _event_reg = new DefaultEventRegistrar();
	private final ICommandRegistrar _cmd_reg = new DefaultCommandRegistrar();

	@Override
	public int findAPI(Class<?> apiClass, XmppAPIHandler h) {

		if (apiClass.isInstance(this)) {
			return h.onAPI(apiClass, this);

		} else if (apiClass.isInstance(this._event_reg)) {
			return h.onAPI(apiClass, this._event_reg);

		} else if (apiClass.isInstance(this._cmd_reg)) {
			return h.onAPI(apiClass, this._cmd_reg);

		} else {
			return XmppAPI.find_continue;
		}
	}

}
