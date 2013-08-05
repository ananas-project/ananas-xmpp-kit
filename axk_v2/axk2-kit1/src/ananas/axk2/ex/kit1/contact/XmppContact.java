package ananas.axk2.ex.kit1.contact;

import java.util.List;

import ananas.axk2.core.XmppAddress;

public interface XmppContact extends AttributeSet {

	List<XmppResource> listResources();

	List<XmppGroup> listOwnerGroups();

	XmppAddress getAddress();

}
