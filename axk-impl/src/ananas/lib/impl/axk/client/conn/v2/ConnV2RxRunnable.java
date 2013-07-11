package ananas.lib.impl.axk.client.conn.v2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

import ananas.lib.axk.constant.XmppStatus;
import ananas.lib.axk.engine.XAccount;
import ananas.lib.axk.engine.XContext;
import ananas.lib.axk.engine.XContextFactory;
import ananas.lib.axk.engine.XEngineListener;
import ananas.lib.axk.engine.XPhase;
import ananas.lib.axk.engine.XSocketContext;
import ananas.lib.axk.engine.util.DefaultEngineRunner;
import ananas.lib.axk.engine.util.DefaultXContext;
import ananas.lib.axk.engine.util.EngineRunner;

public class ConnV2RxRunnable implements Runnable {

	private boolean m_is_closed = false;
	private int m_retry_interval = 500;
	private XAccount mAccount;
	private final ConnV2Runner m_runner;
	private XmppStatus m_current_status = XmppStatus.init;
	private ConnV2TxRunnable m_tx_runn;

	static final int min_retry_interval = 1000;

	public ConnV2RxRunnable(ConnV2Runner runner, ConnV2TxRunnable tx_runn) {
		this.m_runner = runner;
		this.m_tx_runn = tx_runn;
	}

	@Override
	public void run() {

		final Thread thd = this.m_runner.startThread(this.m_tx_runn, "Tx");

		this.__setPhase(XmppStatus.init);
		this.m_retry_interval = min_retry_interval;

		for (int i = 0; !this.m_is_closed; i++) {
			// connect
			this.__setPhase(XmppStatus.connect);
			boolean success = this.__doConnect();
			// sleep to retry
			if (this.m_is_closed) {
				this.__setPhase(XmppStatus.closed);
				break;
			} else {
				if ((i == 0) && (!success)) {
					this.__setPhase(XmppStatus.error);
					break;
				} else {
					this.__setPhase(XmppStatus.dropped);
				}
			}
			int timeout = this.m_retry_interval;
			this.m_retry_interval *= 2;
			if (timeout < min_retry_interval) {
				timeout = min_retry_interval;
			}
			for (; !this.m_is_closed;) {
				int step;
				if (timeout <= 0) {
					break;
				} else if (timeout < (2 * 60 * 1000)) {
					step = 1000;
				} else if (timeout < (10 * 60 * 1000)) {
					step = 1000 * 5;
				} else {
					step = 1000 * 60;
				}
				System.out.println(this + ".retryInSeconds : "
						+ (timeout / 1000));
				this._safe_sleep(step);
				timeout -= step;
			}
		}

		try {
			this.m_tx_runn.close();
			thd.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private final XEngineListener m_engine_listener = new XEngineListener() {

		private Map<XPhase, XmppStatus> m_map;

		@Override
		public void onStanzaElement(XContext context, Element element) {
			ConnV2RxRunnable.this.m_runner.getCallback().onStanza(element);
		}

		@Override
		public void onPhaseChanged(XContext context, XPhase phase) {

			ConnV2RunnerCallback callback = ConnV2RxRunnable.this.m_runner
					.getCallback();

			if (XPhase.online.equals(phase)) {

				ConnV2RxRunnable.this.m_retry_interval = min_retry_interval;
				ConnV2RxRunnable.this.m_is_success = true;

				XSocketContext sock = context.getSocketContext();

				String jid = context.getContextControllerAgent()
						.getBindedFullJID();
				InputStream in = null;
				OutputStream out = null;
				try {
					in = sock.getInput();
					out = sock.getOutput();
				} catch (IOException e) {
					e.printStackTrace();
				}
				callback.onStatusOnline(jid, in, out);
			}
			XmppStatus status = this.phase2status(phase);
			ConnV2RxRunnable.this.__setPhase(status);
		}

		private XmppStatus phase2status(XPhase phase) {
			Map<XPhase, XmppStatus> map = this.m_map;
			if (map == null) {
				map = new HashMap<XPhase, XmppStatus>();
				map.put(XPhase.init, XmppStatus.init);
				map.put(XPhase.connect, XmppStatus.connect);
				map.put(XPhase.tls, XmppStatus.starttls);
				map.put(XPhase.sasl, XmppStatus.startsasl);
				map.put(XPhase.bind, XmppStatus.bind);
				map.put(XPhase.online, XmppStatus.online);
				map.put(XPhase.error, XmppStatus.error);
				map.put(XPhase.closed, XmppStatus.closed);
				this.m_map = map;
			}
			XmppStatus ret = map.get(phase);
			if (ret == null) {
				ret = XmppStatus.init;
			}
			return ret;
		}
	};
	private boolean m_is_success;

	private boolean __doConnect() {
		XSocketContext sock = null;
		try {
			this.m_is_success = false;
			XAccount account = this.__getAccount();
			XEngineListener listener = this.m_engine_listener;
			DefaultXContext context = XContextFactory.Util.getFactory()
					.createMutableContext(account, listener);
			sock = context.getSocketContext();
			EngineRunner runner = new DefaultEngineRunner(context);
			runner.run();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println(e);
		}

		try {
			sock.getInput().close();
		} catch (Exception e) {
		}
		try {
			sock.getOutput().close();
		} catch (Exception e) {
		}
		try {
			sock.getSocket().close();
		} catch (Exception e) {
		}

		return this.m_is_success;
	}

	private XAccount __getAccount() {
		XAccount acc = this.mAccount;
		if (acc == null) {
			acc = this.m_runner.getXAccount();
			this.mAccount = acc;
		}
		return acc;
	}

	private void __setPhase(XmppStatus status) {
		XmppStatus old = this.__swapPhase(status);
		if (!status.equals(old)) {
			this.m_runner.getCallback().onStatusChanged(old, status);
		}
	}

	private synchronized XmppStatus __swapPhase(XmppStatus status) {
		XmppStatus old = this.m_current_status;
		this.m_current_status = status;
		return old;
	}

	private void _safe_sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		this.m_is_closed = true;
	}

	public XmppStatus getStatus() {
		return this.m_current_status;
	}

}
