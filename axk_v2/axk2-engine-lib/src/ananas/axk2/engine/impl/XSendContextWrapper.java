package ananas.axk2.engine.impl;

import java.io.OutputStream;

import ananas.axk2.core.XmppCommandStatus;
import ananas.axk2.engine.XSendContext;
import ananas.axk2.engine.api.XEncoding;
import ananas.axk2.engine.api.XSubConnection;
import ananas.axk2.engine.api.XThreadRuntime;

public class XSendContextWrapper implements Task {

	private XSendContext _cont;
	private XThreadRuntime _tr;

	public XSendContextWrapper(XSendContext context, XThreadRuntime tr) {
		this._tr = tr;
		this._cont = context;
	}

	public Task getTask() {
		return this;
	}

	@Override
	public void run() {
		try {
			_cont.onStep(XmppCommandStatus.sending, null);
			XSubConnection subcon = _tr.getCurrentSubConnection();
			OutputStream out = subcon.getOnlineOutput();
			String s = _cont.getContent();
			out.write(s.getBytes(XEncoding.theDefault));
			out.flush();
			_cont.onStep(XmppCommandStatus.success, null);
		} catch (Exception e) {
			_cont.onStep(XmppCommandStatus.error, e);
		}
	}

	@Override
	public void cancel() {
		Exception e = new RuntimeException("cancel by tx thread");
		_cont.onStep(XmppCommandStatus.error, e);
	}

}
