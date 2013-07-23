package impl.ananas.axk2.core.event;

import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.event.BindEvent;
import ananas.axk2.core.event.BindEventFactory;

public class TheBindEventFactory implements BindEventFactory {

	@Override
	public BindEvent create(XmppConnection conn, XmppAddress bind) {
		return new MyEvent(conn, bind);
	}

	class MyEvent implements BindEvent {

		private final XmppConnection _conn;
		private final XmppAddress _bind;

		public MyEvent(XmppConnection conn, XmppAddress bind) {
			this._conn = conn;
			this._bind = bind;
		}

		@Override
		public XmppConnection getConnection() {
			return this._conn;
		}

		@Override
		public void onReceiveBy(XmppFilter filter) {
		}

		@Override
		public XmppAddress getBind() {
			return this._bind;
		}
	}
}
