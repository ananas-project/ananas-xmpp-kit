package ananas.axk2.ex.kit1;

public interface IContactManager {

	XmppContact getContact(String jid, boolean create);

	XmppResource getResource(String jid, boolean create);

	XmppGroup getGroup(String qName, boolean create);

}
