package ananas.lib.axk;

public interface XmppEvent {

	void onReceiveByListener(XmppEventListener listener);

	void onFilter(XmppFilter filter);

}
