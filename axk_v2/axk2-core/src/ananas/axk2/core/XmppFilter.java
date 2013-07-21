package ananas.axk2.core;

public interface XmppFilter extends XmppAPIProvider {

	void bind(XmppConnection connection);

	void unbind(XmppConnection connection);

	XmppConnection getConnection();

	XmppCommand filter(XmppCommand command);

	XmppEvent filter(XmppEvent event);

}
