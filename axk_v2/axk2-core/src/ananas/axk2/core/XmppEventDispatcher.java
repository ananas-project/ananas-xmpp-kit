package ananas.axk2.core;

public interface XmppEventDispatcher {

	void dispatch(XmppEvent event);

	void addEventListener(XmppEventListener listener);

	void removeEventListener(XmppEventListener listener);

}
