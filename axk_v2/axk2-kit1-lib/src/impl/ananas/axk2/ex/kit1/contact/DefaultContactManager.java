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
	private XmppContact _self;
	private final String _self_jid;

	public DefaultContactManager(String self_jid) {
		this._self_jid = self_jid;
		this._map_attr = new Hashtable<String, Object>();
		this._map_group = new Hashtable<String, XmppGroup>();
		this._map_contact = new Hashtable<String, XmppContact>();
		this._map_res = new Hashtable<String, XmppResource>();
	}

	@Override
	public XmppContact getContact(String jid, boolean create) {
		XmppContact contact = this._map_contact.get(jid);
		if (contact == null)
			if (create) {
				XmppAddress xaddr = new DefaultAddress(jid);
				xaddr = xaddr.toPure();
				String key = xaddr.toString();
				contact = new MyContact(xaddr, this);
				this._map_contact.put(key, contact);
			}
		return contact;
	}

	@Override
	public XmppResource getResource(String jid, boolean create) {
		XmppResource res = this._map_res.get(jid);
		if (res == null)
			if (create) {
				XmppAddress xaddr = new DefaultAddress(jid);
				// get contact
				XmppContact contact = this.getContact(
						xaddr.toPure().toString(), true);
				// create res
				xaddr = xaddr.toFull();
				String key = xaddr.toString();
				res = new MyResource(xaddr, this, contact);
				this._map_res.put(key, res);
			}
		return res;
	}

	@Override
	public XmppGroup getGroup(String qName, boolean create) {
		XmppGroup group = this._map_group.get(qName);
		if (group == null)
			if (create) {
				group = new MyGroup(qName, this);
				this._map_group.put(qName, group);
			}
		return group;
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

	@Override
	public XmppContact getSelf() {
		XmppContact self = this._self;
		if (self == null) {
			String jid = this._self_jid;
			self = this.getContact(jid, true);
			_self = self;
		}
		return self;
	}

}
