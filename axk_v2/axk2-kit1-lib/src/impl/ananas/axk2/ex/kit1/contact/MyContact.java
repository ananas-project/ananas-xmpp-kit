package impl.ananas.axk2.ex.kit1.contact;

import java.util.List;

import ananas.axk2.core.XmppAddress;
import ananas.axk2.ex.kit1.contact.XmppContact;
import ananas.axk2.ex.kit1.contact.XmppGroup;
import ananas.axk2.ex.kit1.contact.XmppResource;

public class MyContact implements XmppContact, IContactNode {

	private final IContactManagerInner _inner;
	private String _local_name;
	private String _q_name;
	private final XmppAddress _address;

	public MyContact(XmppAddress addr, IContactManagerInner inner) {
		_inner = inner;
		_address = addr;
	}

	@Override
	public Object getAttribute(String key) {
		String qn = this.__qName4key(key);
		return _inner.getAttribute(qn);
	}

	private String __qName4key(String key) {
		return this.getQName() + "##" + key;
	}

	@Override
	public void setAttribute(String key, Object value) {
		String qn = this.__qName4key(key);
		_inner.setAttribute(qn, value);
	}

	@Override
	public List<XmppResource> listResources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<XmppGroup> listOwnerGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppAddress getAddress() {
		return this._address;
	}

	@Override
	public IContactNode getParent() {
		return this._inner;
	}

	@Override
	public String getLocalName() {
		String ln = this._local_name;
		if (ln == null) {
			ln = this.getAddress().toString();
			this._local_name = ln;
		}
		return ln;
	}

	@Override
	public String getQName() {
		String qn = this._q_name;
		if (qn == null) {
			qn = this.getParent().getQName() + "##" + this.getLocalName();
			this._q_name = qn;
		}
		return qn;
	}

}
