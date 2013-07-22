package impl.ananas.axk2.core.base;

import org.w3c.dom.Element;

import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.XmppStatus;
import ananas.axk2.core.api.IClient;
import ananas.axk2.engine.XEngine;
import ananas.axk2.engine.XEngineFactory;
import ananas.axk2.engine.XEngineListener;
import ananas.axk2.engine.util.DefaultEngineContext;
import ananas.lib.util.logging.Logger;

public class Engine extends Filter implements IClient {

	final static Logger log = Logger.Agent.getLogger();
	private XEngine _engine;

	private XEngine __getEngine() {

		XEngine engine = this._engine;
		if (engine != null) {
			return engine;
		}

		this.getConnection().send(new MyTraceCmd());
		this.getConnection().dispatch(new MyTraceEvent());

		XEngineFactory factory = XEngineFactory.Agent.getDefault();
		DefaultEngineContext context = new DefaultEngineContext();
		context._listener = this._event_hub;
		context._account = this.getConnection().getAccount();
		log.info("create xmpp client to " + context._account);
		this._engine = engine = factory.createEngine(context);

		return engine;
	}

	class MyEventHub implements XEngineListener {

		@Override
		public void onStanza(XEngine engine, Element stanza) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPhaseChanged(XEngine engine, XmppStatus oldPhase,
				XmppStatus newPhase) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onBind(XEngine engine, XmppAddress jid) {
			Engine.this._bind_jid = jid;
		}
	}

	private final MyEventHub _event_hub = new MyEventHub();
	private XmppAddress _bind_jid;

	@Override
	public void connect() {
		XEngine engine = this.__getEngine();
		engine.connect();
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

	@Override
	public XmppStatus getStatus() {
		XEngine engine = this.__getEngine();
		return engine.getStatus();
	}

	@Override
	public XmppStatus getPhase() {
		XEngine engine = this.__getEngine();
		return engine.getPhase();
	}

	@Override
	public XmppAddress getBind() {
		return this._bind_jid;
	}

}
