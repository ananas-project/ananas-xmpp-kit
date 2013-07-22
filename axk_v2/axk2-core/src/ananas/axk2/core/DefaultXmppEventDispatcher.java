package ananas.axk2.core;

public class DefaultXmppEventDispatcher implements XmppEventDispatcher {

	private XmppEventListener _list;

	@Override
	public void dispatch(XmppEvent event) {
		XmppEventListener listener = this._list;
		if (listener != null) {
			listener.onEvent(event);
		}
	}

	@Override
	public void addEventListener(XmppEventListener listener) {
		this._list = listener;
	}

	@Override
	public void removeEventListener(XmppEventListener listener) {
		this._list = null;
	}

}
