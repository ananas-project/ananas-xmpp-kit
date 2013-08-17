package impl.ananas.axk2.ex.kit1.contact;

import ananas.axk2.core.XmppAddress;
import ananas.axk2.ex.kit1.contact.IContactModel;
import ananas.axk2.ex.kit1.contact.XmppContact;
import ananas.axk2.ex.kit1.contact.XmppResource;

final class MyResource implements XmppResource {

	private String _string;
	private final XmppAddress _address;
	private final XmppContact _contact;
	private final IContactModel _contact_man;

	public MyResource(XmppAddress addr, IContactModel cm, XmppContact contact) {
		_address = addr;
		_contact = contact;
		_contact_man = cm;
	}

	@Override
	public Object getAttribute(String key) {
		key = this.__genKey(key);
		return this._contact_man.getAttribute(key);
	}

	@Override
	public void setAttribute(String key, Object value) {
		key = this.__genKey(key);
		this._contact_man.setAttribute(key, value);
	}

	private String __genKey(String key) {
		return (this + "." + key);
	}

	@Override
	public XmppContact getOwnerContact() {
		return this._contact;
	}

	@Override
	public XmppAddress getAddress() {
		return _address;
	}

	@Override
	public String toString() {
		String str = _string;
		if (str == null) {
			_string = str = "XmppResource('" + _address + "')";
		}
		return str;
	}

	@Override
	public IContactModel getModel() {
		return this._contact_man;
	}

}
