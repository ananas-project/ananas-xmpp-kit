package impl.ananas.axk2.core.base;

import org.w3c.dom.Element;

import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.XmppStatus;
import ananas.axk2.core.api.IClient;
import ananas.axk2.core.api.IEventRegistrar;
import ananas.axk2.core.event.PhaseEvent;
import ananas.axk2.core.event.PhaseEventFactory;
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

		private PhaseEventFactory _thePhaseEventFactory;
		private IEventRegistrar _theIEventRegistrar;
		private XmppConnection _theConnection;

		@Override
		public void onStanza(XEngine engine, Element stanza) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPhaseChanged(XEngine engine, XmppStatus oldPhase,
				XmppStatus newPhase) {

			XmppConnection conn = this.__getConnection();
			PhaseEventFactory factory = this.__getPhaseEventFactory();
			PhaseEvent event = factory.create(conn, oldPhase, newPhase);
			conn.dispatch(event);
		}

		private XmppConnection __getConnection() {
			XmppConnection conn = this._theConnection;
			if (conn == null) {
				conn = Engine.this.getConnection();
				this._theConnection = conn;
			}
			return conn;
		}

		private PhaseEventFactory __getPhaseEventFactory() {
			PhaseEventFactory factory = this._thePhaseEventFactory;
			if (factory == null) {
				IEventRegistrar reg = this.__getIEventRegistrar();
				factory = (PhaseEventFactory) reg
						.getFactory(PhaseEventFactory.class);
				this._thePhaseEventFactory = factory;
			}
			return factory;
		}

		private IEventRegistrar __getIEventRegistrar() {
			IEventRegistrar reg = this._theIEventRegistrar;
			if (reg == null) {
				XmppConnection conn = this.__getConnection();
				reg = (IEventRegistrar) conn.getAPI(IEventRegistrar.class);
				this._theIEventRegistrar = reg;
			}
			return reg;
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
