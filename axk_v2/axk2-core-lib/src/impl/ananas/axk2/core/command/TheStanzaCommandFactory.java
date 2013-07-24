package impl.ananas.axk2.core.command;

import impl.ananas.axk2.core.DefaultStanzaContext;
import ananas.axk2.core.XmppCommandListener;
import ananas.axk2.core.XmppCommandStatus;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.core.command.StanzaCommandFactory;

public class TheStanzaCommandFactory implements StanzaCommandFactory {

	@Override
	public StanzaCommand create(XmppConnection conn) {
		return new MyCommand(conn);
	}

	static class MyCommand extends DefaultStanzaContext implements
			StanzaCommand {

		private XmppCommandListener _listener;
		private XmppCommandStatus _status = XmppCommandStatus.initial;

		public MyCommand(XmppConnection conn) {
			super(conn.getContext());
		}

		@Override
		public void onSendBy(XmppFilter filter) {
		}

		@Override
		public void setListener(XmppCommandListener listener) {
			this._listener = listener;
		}

		@Override
		public void setStatus(XmppCommandStatus status) {
			if (status == null)
				return;
			final XmppCommandStatus old = this._status;
			this._status = status;
			if (!status.equals(old)) {
				XmppCommandListener li = this._listener;
				if (li != null) {
					li.onStatusChanged(old, status);
				}
			}
		}

		@Override
		public XmppCommandStatus getStatus() {
			return this._status;
		}
	}

}
