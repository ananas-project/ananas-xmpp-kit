package impl.ananas.axk2.core.base;

import org.w3c.dom.Element;

import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppStatus;
import ananas.axk2.engine.XEngine;
import ananas.axk2.engine.XEngineListener;
import ananas.lib.util.logging.Logger;

public class EventExceptionWall implements XEngineListener {

	final static Logger log = Logger.Agent.getLogger();

	private final XEngineListener _tar;

	public EventExceptionWall(XEngineListener target) {
		this._tar = target;
	}

	private void _onException(Exception e) {
		log.error(e);
	}

	@Override
	public void onStanza(XEngine engine, Element stanza) {
		try {
			_tar.onStanza(engine, stanza);
		} catch (Exception e) {
			this._onException(e);
		}
	}

	@Override
	public void onBind(XEngine engine, XmppAddress jid) {
		try {
			_tar.onBind(engine, jid);
		} catch (Exception e) {
			this._onException(e);
		}
	}

	@Override
	public void onPhaseChanged(XEngine engine, XmppStatus oldPhase,
			XmppStatus newPhase) {
		try {
			_tar.onPhaseChanged(engine, oldPhase, newPhase);
		} catch (Exception e) {
			this._onException(e);
		}
	}

}
