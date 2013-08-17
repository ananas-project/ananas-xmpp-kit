package impl.ananas.axk2.ex.kit1;

import impl.ananas.axk2.ex.kit1.contact.DefaultContactManager;

import java.util.List;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.ex.kit1.contact.IContactModel;
import ananas.axk2.ex.kit1.contact.XmppContact;
import ananas.axk2.ex.kit1.contact.XmppGroup;
import ananas.axk2.ex.kit1.contact.XmppResource;

public class TContactModel extends TFilter implements IContactModel {

	private IContactModel _cm = null;

	public TContactModel() {
	}

	@Override
	public void bind(XmppConnection connection) {
		super.bind(connection);
		XmppAccount account = connection.getAccount();
		String addr = account.jid().toString();
		_cm = new DefaultContactManager(addr);
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

	@Override
	public XmppContact getSelf() {
		return _cm.getSelf();
	}

}
