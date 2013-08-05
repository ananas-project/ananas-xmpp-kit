package impl.ananas.axk2.ex.kit1;

import impl.ananas.axk2.ex.kit1.contact.DefaultContactManager;

import java.util.List;

import ananas.axk2.ex.kit1.contact.IContactManager;
import ananas.axk2.ex.kit1.contact.XmppContact;
import ananas.axk2.ex.kit1.contact.XmppGroup;
import ananas.axk2.ex.kit1.contact.XmppResource;

public class TContactManager extends TFilter implements IContactManager {

	private final IContactManager _cm = new DefaultContactManager();

	public TContactManager() {
	}

	public XmppContact getContact(String jid, boolean create) {
		return _cm.getContact(jid, create);
	}

	public XmppResource getResource(String jid, boolean create) {
		return _cm.getResource(jid, create);
	}

	public XmppGroup getGroup(String qName, boolean create) {
		return _cm.getGroup(qName, create);
	}

	public List<XmppGroup> listGroups() {
		return _cm.listGroups();
	}

	public List<XmppContact> listContacts() {
		return _cm.listContacts();
	}

	public List<XmppResource> listResources() {
		return _cm.listResources();
	}

	@Override
	public Object getAttribute(String key) {
		return _cm.getAttribute(key);
	}

	@Override
	public void setAttribute(String key, Object value) {
		_cm.setAttribute(key, value);
	}

}
