package ananas.axk2.engine.impl;

import java.util.ArrayList;
import java.util.List;

import ananas.axk2.core.XmppCommandStatus;
import ananas.axk2.engine.XIOTask;
import ananas.axk2.engine.api.XThreadRuntime;
import ananas.lib.util.logging.Logger;

public class DefaultRunLoop implements RunLoop {

	final static Logger log = Logger.Agent.getLogger();

	private final List<XIOTask> _list = new ArrayList<XIOTask>();
	private final Object _sync_obj = new Object();

	@Override
	public void push_back(XIOTask runn) {
		this.__non_block_io(runn, -1, false);
		synchronized (this._sync_obj) {
			this._sync_obj.notifyAll();
		}
	}

	@Override
	public void push_front(XIOTask runn) {
		this.__non_block_io(runn, 0, false);
		synchronized (this._sync_obj) {
			this._sync_obj.notifyAll();
		}
	}

	@Override
	public void clear() {
		for (;;) {
			XIOTask runn = this.pop(false);
			if (runn == null) {
				break;
			}
			this.__cancel(runn);
		}
	}

	private void __cancel(XIOTask task) {
		try {
			task.onStep(XmppCommandStatus.cancel, null);
		} catch (Exception e) {
			log.error(e);
			task.onStep(XmppCommandStatus.error, e);
		}
	}

	private synchronized XIOTask __non_block_io(XIOTask in, int index,
			boolean is_get) {
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
	public XIOTask pop(boolean wait) {
		for (;;) {
			XIOTask runn = this.__non_block_io(null, 0, true);
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
	public void run(int times, XThreadRuntime rt) {
		for (; times > 0; times--) {
			XIOTask runn = this.pop(true);
			this.__exe(runn, rt);
		}
	}

	private void __exe(XIOTask runn, XThreadRuntime rt) {
		try {
			runn.run(rt);
		} catch (Exception e) {
			log.error(e);
			runn.onStep(XmppCommandStatus.error, e);
		}
	}

}
