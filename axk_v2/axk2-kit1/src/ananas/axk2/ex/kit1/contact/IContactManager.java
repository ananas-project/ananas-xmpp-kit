package ananas.axk2.ex.kit1.contact;

import java.util.List;

public interface IContactManager extends AttributeSet {

	XmppContact getContact(String jid, boolean create);

	XmppResource getResource(String jid, boolean create);

	XmppGroup getGroup(String qName, boolean create);

	List<XmppGroup> listGroups();

	List<XmppContact> listContacts();

	List<XmppResource> listResources();

}
