package impl.ananas.axk2.ex.kit1;

import impl.ananas.axk2.ex.kit1.terminal.TerminalCallback;
import impl.ananas.axk2.ex.kit1.terminal.TerminalRunner;
import ananas.axk2.core.XmppConnection;

public class TTerminal extends TFilter {

	private Thread _daemon_thread;

	@Override
	public void bind(XmppConnection connection) {
		super.bind(connection);
		this.start_daemon();
	}

	private void start_daemon() {
		Runnable runn = TerminalRunner.Factory.create(new MyCallback());
		Thread thd = (new Thread(runn));
		this._daemon_thread = thd;
		thd.start();
	}

	class MyCallback implements TerminalCallback {

		@Override
		public boolean isNeedExit() {
			Thread thd = Thread.currentThread();
			return (!thd.equals(TTerminal.this._daemon_thread));
		}

		@Override
		public XmppConnection getConnection() {
			return TTerminal.this.getConnection();
		}
	}

	@Override
	public void unbind(XmppConnection connection) {
		super.unbind(connection);
		this._daemon_thread = null;
	}

}
