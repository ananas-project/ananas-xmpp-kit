package ananas.axk2.stringio;

public class MessagePump implements Runnable {

	private final IStringIO _io;
	private final IMessageListener _listener;
	private boolean _do_running;
	private boolean _is_running;

	public MessagePump(IStringIO io, IMessageListener listener) {
		this._io = io;
		this._listener = listener;
	}

	@Override
	public void run() {
		this._is_running = true;
		int timeinterval = 1;
		for (; this._do_running;) {
			if (this.__doLoop(1)) {
				timeinterval = 1;
				continue;
			} else {
				try {
					Thread.sleep(timeinterval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				timeinterval *= 2;
				timeinterval = Math.min(1000, timeinterval);
			}
		}
		this._is_running = false;
	}

	private boolean __doLoop(int times) {
		String event = null;
		try {
			event = _io.io(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (event == null) {
			return false;
		}
		try {
			_listener.onEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void start() {
		this._do_running = true;
		(new Thread(this)).start();
	}

	public void stop() {
		this._do_running = false;
	}

	public boolean isRunning() {
		return this._is_running;
	}
}
