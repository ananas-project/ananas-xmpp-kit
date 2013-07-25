package impl.ananas.axk2.ex.kit1;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ananas.axk2.core.DefaultAddress;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.ex.kit1.IContactManager;
import ananas.axk2.ex.kit1.XmppContact;
import ananas.axk2.ex.kit1.XmppGroup;
import ananas.axk2.ex.kit1.XmppResource;

public class TContactManager extends TFilter implements IContactManager {

	private final Map<String, XmppResource> _resource_table;
	private final Map<String, MyXmppContact> _contact_table;

	public TContactManager() {
		this._contact_table = new Hashtable<String, MyXmppContact>();
		this._resource_table = new Hashtable<String, XmppResource>();
	}

	@Override
	public XmppContact getContact(String jid, boolean create) {
		XmppContact contact = this._contact_table.get(jid);
		if ((contact == null) && (create)) {
			final XmppAddress addr = new DefaultAddress(jid);
			final XmppAddress addrPure = addr.toPure();
			contact = this.__getContact(addrPure, create);
		}
		return contact;
	}

	private MyXmppContact __getContact(XmppAddress jid, boolean create) {
		jid = jid.toPure();
		MyXmppContact contact = this._contact_table.get(jid.toString());
		if ((contact == null) && (create)) {
			contact = new MyXmppContact(jid);
			this._contact_table.put(jid.toString(), contact);
		}
		return contact;
	}

	@Override
	public XmppResource getResource(String jid, boolean create) {
		XmppResource res = this._resource_table.get(jid);
		if ((res == null) && (create)) {
			final XmppAddress addr = new DefaultAddress(jid);
			final XmppAddress addrFull = addr.toFull();
			final XmppAddress addrPure = addr.toPure();
			MyXmppContact contact = this.__getContact(addrPure, create);
			res = new MyXmppResource(contact, addrFull);
			contact.__addResource(res);
			this._resource_table.put(addrFull.toString(), res);
		}
		return res;
	}

	class MyXmppResource implements XmppResource {

		private final XmppContact _owner;
		private final XmppAddress _address;

		public MyXmppResource(XmppContact contact, XmppAddress jid) {
			this._owner = contact;
			this._address = jid;
		}

		@Override
		public XmppContact getOwnerContact() {
			return this._owner;
		}

		@Override
		public XmppAddress getAddress() {
			return this._address;
		}
	}

	class MyXmppContact implements XmppContact {

		private final XmppAddress _address;
		private final List<XmppResource> _resources;

		public MyXmppContact(XmppAddress jid) {
			this._address = jid;
			this._resources = new Vector<XmppResource>();
		}

		public void __addResource(XmppResource res) {
			this._resources.add(res);
		}

		@Override
		public List<XmppResource> listResources() {
			return new ArrayList<XmppResource>(this._resources);
		}

		@Override
		public XmppAddress getAddress() {
			return this._address;
		}

		@Override
		public List<XmppGroup> listOwnerGroups() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	@Override
	public XmppGroup getGroup(String qName, boolean create) {
		// TODO Auto-generated method stub
		return null;
	}

}
