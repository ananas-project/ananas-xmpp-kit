package ananas.lib.axk.element.stream;

public class Xmpp_stream {

	private XmppStreamListener mListener;

	public XmppStreamListener getListener() {
		return this.mListener;
	}

	public void setListener(XmppStreamListener listener) {
		this.mListener = listener;
	}

	public void dispatchRxObject(Object object) {
		this.mListener.onReceive(this, object);
	}

}
