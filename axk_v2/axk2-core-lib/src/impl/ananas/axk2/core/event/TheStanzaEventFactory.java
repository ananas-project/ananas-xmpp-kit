package impl.ananas.axk2.core.event;

import impl.ananas.axk2.core.DefaultStanzaContext;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.event.StanzaEvent;
import ananas.axk2.core.event.StanzaEventFactory;

public class TheStanzaEventFactory implements StanzaEventFactory {

	@Override
	public StanzaEvent create(XmppConnection conn) {
		return new MyEvent(conn);
	}

	class MyEvent extends DefaultStanzaContext implements StanzaEvent {

		private final XmppConnection _conn;

		public MyEvent(XmppConnection conn) {
			super(conn.getContext());
			this._conn = conn;
		}

		@Override
		public XmppConnection getConnection() {
			return this._conn;
		}

		@Override
		public void onReceiveBy(XmppFilter filter) {
		}

	}

}
