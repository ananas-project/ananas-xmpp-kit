package ananas.axk2.ex.kit1;

import java.util.List;

public interface XmppGroup {

	List<XmppContact> listMembers();

	String getQName();

	String getName();
}
