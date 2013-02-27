package ananas.lib.impl.axk.client.target;

import ananas.lib.axk.XmppClientExAPI;
import ananas.lib.axk.XmppStatus;
import ananas.lib.axk.api.IExConnection;
import ananas.lib.axk.api.IExCore;
import ananas.lib.impl.axk.client.conn.XmppConnection;
import ananas.lib.impl.axk.client.conn.XmppConnectionListener;

public class Tar_connection extends Tar_abstractClient implements IExConnection {

	private XmppStatus mStatus = XmppStatus.init;
	private Worker mCurWorker;

	public Tar_connection() {
	}

	@Override
	public XmppClientExAPI getExAPI(Class<? extends XmppClientExAPI> apiClass) {
		if (apiClass.equals(IExConnection.class)) {
			IExConnection api = this;
			return api;
		} else {
			return super.getExAPI(apiClass);
		}
	}

	@Override
	public void setStatus(XmppStatus phase) {

		if (phase == null) {
			return;
		} else if (phase.equals(XmppStatus.online)) {
			this.connect();
		} else if (phase.equals(XmppStatus.offline)) {
			this.disconnect();
		} else if (phase.equals(XmppStatus.closed)) {
			this.close();
		}
	}

	@Override
	public XmppStatus getStatus() {
		return this.mStatus;
	}

	@Override
	public XmppStatus getPhase() {
		Worker wkr = this.mCurWorker;
		if (wkr == null) {
			return XmppStatus.offline;
		} else {
			return wkr.getPhase();
		}
	}

	@Override
	public void reset() {
		this.mStatus = XmppStatus.init;
		this._setCurWorker(null);
	}

	@Override
	public void close() {
		this.mStatus = XmppStatus.closed;
		this._setCurWorker(null);
	}

	@Override
	public void connect() {
		this.mStatus = XmppStatus.online;
		Worker wkr = new Worker();
		this._setCurWorker(wkr);
	}

	private void _setCurWorker(Worker wkr) {
		Worker pold;
		synchronized (this) {
			pold = this.mCurWorker;
			this.mCurWorker = wkr;
		}
		if (pold != null) {
			pold.close();
		}
		if (wkr != null) {
			wkr.open();
		}
	}

	@Override
	public void disconnect() {
		this.mStatus = XmppStatus.offline;
		this._setCurWorker(null);
	}

	class Worker implements Runnable, XmppConnectionListener {

		private boolean mIsClose = false;
		private XmppStatus mPhase;
		private int mRetryDelayMS;

		public Worker() {
		}

		@Override
		public void run() {

			try {
				for (; !this.mIsClose;) {

					// delay to conn
					int timeout = this.mRetryDelayMS;
					final int step = 1000;
					for (; !this.mIsClose;) {
						if (timeout > 0) {

							System.out.println("re-connect after " + timeout
									+ " ms");

							int ms = Math.min(timeout, step);
							this._safe_sleep(ms);
							timeout -= ms;
						} else {
							break;
						}
					}

					if (this.mIsClose) {
						break;
					}

					this.setCurPhase(XmppStatus.logining);

					IExCore api = Tar_connection.this.getCoreApi();
					XmppConnection conn = new XmppConnection(api.getAccount(),
							api.getEnvironment(), this);
					conn.run();
					String lastError = conn.getLastError();
					if (lastError == null) {
						this.mRetryDelayMS = 0;
					} else {
						int val = this.mRetryDelayMS * 2;
						if (val < 1000)
							val = 1000;
						if (val <= (3600 * 1000))
							this.mRetryDelayMS = val;
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("here must not get a exception!");
			}

		}

		private void _safe_sleep(int ms) {
			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		private void setCurPhase(XmppStatus status) {
			XmppStatus old;
			synchronized (this) {
				old = this.mPhase;
				this.mPhase = status;
			}
			if (old != status) {
				Tar_connection.this.onPhaseChanged(this, old, status);
			}
		}

		public XmppStatus getPhase() {
			return this.mPhase;
		}

		public void close() {
			this.mIsClose = true;
		}

		public void open() {
			Thread thd = new Thread(this);
			thd.setName("axk-worker-thread");
			thd.start();
		}

		@Override
		public void onReceive(Object object) {
			System.out.println(this + ".onResceive:" + object);
		}
	}

	public void onPhaseChanged(Worker worker, XmppStatus oldStatus,
			XmppStatus newStatus) {

		System.out.println(worker + ".onPhaseChanged: " + oldStatus + " -> "
				+ newStatus);

	}

	public IExCore getCoreApi() {
		return (IExCore) this.getExAPI(IExCore.class);
	}

}
