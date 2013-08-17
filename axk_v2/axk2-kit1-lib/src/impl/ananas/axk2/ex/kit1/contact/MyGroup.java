package impl.ananas.axk2.ex.kit1.contact;

import java.util.List;

import ananas.axk2.ex.kit1.contact.IContactModel;
import ananas.axk2.ex.kit1.contact.XmppContact;
import ananas.axk2.ex.kit1.contact.XmppGroup;

class MyGroup implements XmppGroup {

	private final String _qName;
	private String _string;
	private final IContactModel _contact_mod;

	public MyGroup(String qName, IContactModel cm) {
		this._qName = qName;
		this._contact_mod = cm;
	}

	@Override
	public Object getAttribute(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(String key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<XmppContact> listMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		String str = this._string;
		if (str == null) {
			_string = str = "XmppGroup('" + _qName + "')";
		}
		return str;
	}

	@Override
	public IContactModel getModel() {
		return this._contact_mod;
	}

}
