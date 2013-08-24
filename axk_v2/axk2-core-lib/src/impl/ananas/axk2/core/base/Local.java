package impl.ananas.axk2.core.base;

import impl.ananas.axk2.core.command.DefaultCommandAgent;
import impl.ananas.axk2.core.command.DefaultCommandRegistrar;
import impl.ananas.axk2.core.event.DefaultEventRegistrar;
import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.api.ICommandAgent;
import ananas.axk2.core.api.ICommandRegistrar;
import ananas.axk2.core.api.IEventRegistrar;

public class Local extends Filter {

	private final IEventRegistrar _event_reg = new DefaultEventRegistrar();
	private final ICommandRegistrar _cmd_reg = new DefaultCommandRegistrar();
	private final ICommandAgent _cmd_agent = new DefaultCommandAgent();

	@Override
	public int listAPI(XmppAPIHandler h) {
		XmppAPIHandler.Util.apiOfObject(this._cmd_agent, h);
		XmppAPIHandler.Util.apiOfObject(this._event_reg, h);
		XmppAPIHandler.Util.apiOfObject(this._cmd_reg, h);
		return super.listAPI(h);
	}

}
