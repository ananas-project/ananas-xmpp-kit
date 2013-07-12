package ananas.lib.axk;

public interface XmppCommand {

	void onSendByClient(XmppClient client);

	void onFilter(XmppFilter filter);

}
