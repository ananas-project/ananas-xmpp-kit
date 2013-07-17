package ananas.axk2.engine.impl;

class ThreadRuntime {

	private final Thread _threadRx;
	private boolean _closed;

	public ThreadRuntime() {
		this._threadRx = __newThread(new MyRxRunnable());
	}

	public void start() {
		this._threadRx.start();
	}

	public void stop() {
		this._closed = true;
		// this._threadRx.join();
	}

	class MyTxRunnable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub

		}

	}

	class MyRxRunnable implements Runnable {

		@Override
		public void run() {
			final Thread threadTx = __newThread(new MyTxRunnable());
			threadTx.start();

			try {
				threadTx.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private Thread __newThread(Runnable runnable) {
		return new Thread(runnable);
	}

}
