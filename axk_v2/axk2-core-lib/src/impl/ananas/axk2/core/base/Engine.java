package impl.ananas.axk2.core.base;

import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.api.IClient;
import ananas.lib.util.logging.Logger;

public class Engine extends Filter implements IClient {

	final static Logger log = Logger.Agent.getLogger();

	@Override
	public void connect() {
		// TODO Auto-generated method stub

		this.getConnection().send(new MyTraceCmd());
		this.getConnection().dispatch(new MyTraceEvent());

	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub

	}

	class MyTraceCmd implements XmppCommand {

		@Override
		public void onSendBy(XmppFilter filter) {
			log.trace(this + ".onSendBy() : " + filter);
		}
	}

	class MyTraceEvent implements XmppEvent {

		@Override
		public void onReceiveBy(XmppFilter filter) {
			log.trace(this + ".onReceiveBy() : " + filter);
		}
	}

}
