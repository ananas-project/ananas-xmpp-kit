package ananas.axk2.ex.kit1;

import java.util.List;

import ananas.axk2.core.XmppAddress;

public interface XmppContact {

	List<XmppResource> listResources();

	List<XmppGroup> listOwnerGroups();

	XmppAddress getAddress();

}
