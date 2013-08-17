package ananas.axk2.ex.kit1.contact;

import java.util.List;

public interface XmppGroup extends AttributeSet {

	IContactModel getModel();

	List<XmppContact> listMembers();

	String getName();
}
