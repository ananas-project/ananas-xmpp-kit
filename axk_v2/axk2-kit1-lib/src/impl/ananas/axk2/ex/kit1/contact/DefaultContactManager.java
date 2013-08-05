package impl.ananas.axk2.ex.kit1.contact;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import ananas.axk2.core.DefaultAddress;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.ex.kit1.contact.XmppContact;
import ananas.axk2.ex.kit1.contact.XmppGroup;
import ananas.axk2.ex.kit1.contact.XmppResource;

public class DefaultContactManager implements IContactManagerInner {

	private final Map<String, Object> _map_attr;
	private final Map<String, XmppGroup> _map_group;
	private final Map<String, XmppContact> _map_contact;
	private final Map<String, XmppResource> _map_res;

	public DefaultContactManager() {
		this._map_attr = new Hashtable<String, Object>();
		this._map_group = new Hashtable<String, XmppGroup>();
		this._map_contact = new Hashtable<String, XmppContact>();
		this._map_res = new Hashtable<String, XmppResource>();
	}

	@Override
	public XmppContact getContact(String jid, boolean create) {
		XmppContact contact = this._map_contact.get(jid);
		if ((contact == null) && (create)) {
			XmppAddress xaddr = new DefaultAddress(jid);
			xaddr = xaddr.toPure();
			contact = new MyContact(xaddr, this);
			String key = xaddr.toString();
			this._map_contact.put(key, contact);
		}
		return contact;
	}

	@Override
	public XmppResource getResource(String jid, boolean create) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppGroup getGroup(String qName, boolean create) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<XmppGroup> listGroups() {
		return new ArrayList<XmppGroup>(_map_group.values());
	}

	@Override
	public List<XmppContact> listContacts() {
		return new ArrayList<XmppContact>(_map_contact.values());
	}

	@Override
	public List<XmppResource> listResources() {
		return new ArrayList<XmppResource>(_map_res.values());
	}

	@Override
	public String getLocalName() {
		return "";
	}

	@Override
	public String getQName() {
		return "";
	}

	@Override
	public Object getAttribute(String key) {
		return _map_attr.get(key);
	}

	@Override
	public void setAttribute(String key, Object value) {
		_map_attr.put(key, value);
	}

	@Override
	public IContactNode getParent() {
		return null;
	}

}
