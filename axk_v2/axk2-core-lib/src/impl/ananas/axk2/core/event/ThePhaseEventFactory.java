package impl.ananas.axk2.core.event;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.XmppStatus;
import ananas.axk2.core.event.PhaseEvent;
import ananas.axk2.core.event.PhaseEventFactory;

public class ThePhaseEventFactory implements PhaseEventFactory {

	@Override
	public PhaseEvent create(XmppConnection conn, XmppStatus theOld,
			XmppStatus theNew) {
		MyEvent event = new MyEvent();
		event._conn = conn;
		event._new = theNew;
		event._old = theOld;
		return event;
	}

	class MyEvent implements PhaseEvent {

		private XmppStatus _old;
		private XmppStatus _new;
		private XmppConnection _conn;

		@Override
		public XmppConnection getConnection() {
			return this._conn;
		}

		@Override
		public void onReceiveBy(XmppFilter filter) {
		}

		@Override
		public XmppStatus getNewPhase() {
			return this._new;
		}

		@Override
		public XmppStatus getOldPhase() {
			return this._old;
		}
	}

}
