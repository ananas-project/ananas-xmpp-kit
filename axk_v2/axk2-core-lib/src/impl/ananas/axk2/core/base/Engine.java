package impl.ananas.axk2.core.base;

import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.api.IClient;
import ananas.axk2.engine.XEngine;
import ananas.axk2.engine.XEngineFactory;
import ananas.axk2.engine.util.DefaultEngineContext;
import ananas.lib.util.logging.Logger;

public class Engine extends Filter implements IClient {

	final static Logger log = Logger.Agent.getLogger();
	private XEngine _engine;

	@Override
	public void connect() {
		XEngine engine = this.__getEngine();
		engine.connect();
	}

	private XEngine __getEngine() {

		XEngine engine = this._engine;
		if (engine != null) {
			return engine;
		}

		this.getConnection().send(new MyTraceCmd());
		this.getConnection().dispatch(new MyTraceEvent());

		XEngineFactory factory = XEngineFactory.Agent.getDefault();
		DefaultEngineContext context = new DefaultEngineContext();
		context._account = this.getConnection().getAccount();
		log.info("create xmpp client to " + context._account);
		engine = factory.createEngine(context);

		return engine;
	}

	@Override
	public void disconnect() {
		XEngine engine = this.__getEngine();
		engine.disconnect();
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
