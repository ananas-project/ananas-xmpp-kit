package ananas.axk2.engine.impl;

import java.util.ArrayList;
import java.util.List;

public class DefaultRunLoop implements RunLoop {

	private final List<Task> _list = new ArrayList<Task>();
	private final Object _sync_obj = new Object();

	@Override
	public void push_back(Task runn) {
		this.__non_block_io(runn, -1, false);
		synchronized (this._sync_obj) {
			this._sync_obj.notifyAll();
		}
	}

	@Override
	public void push_front(Task runn) {
		this.__non_block_io(runn, 0, false);
		synchronized (this._sync_obj) {
			this._sync_obj.notifyAll();
		}
	}

	@Override
	public void clear() {
		for (;;) {
			Task runn = this.pop(false);
			if (runn == null) {
				break;
			}
			this.__cancel(runn);
		}
	}

	private void __cancel(Task runn) {
		try {
			runn.cancel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized Task __non_block_io(Task in, int index, boolean is_get) {
		if (is_get) {
			if (_list.size() > 0)
				return _list.remove(0);
		} else {
			if (in != null) {
				if (index < 0)
					_list.add(in);
				else
					_list.add(0, in);
			}
		}
		return null;
	}

	@Override
	public Task pop(boolean wait) {
		for (;;) {
			Task runn = this.__non_block_io(null, 0, true);
			if ((runn == null) && (wait)) {
				synchronized (this._sync_obj) {
					try {
						this._sync_obj.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				continue;
			}
			return runn;
		}
	}

	@Override
	public void run(int times) {
		for (; times > 0; times--) {
			Runnable runn = this.pop(true);
			this.__exe(runn);
		}
	}

	private void __exe(Runnable runn) {
		try {
			runn.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
