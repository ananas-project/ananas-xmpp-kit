package ananas.axk2.core;

import java.util.List;

public interface XmppFilterList extends XmppFilter {

	int insert(XmppFilter filter, int index);

	int append(XmppFilter filter);

	int count();

	XmppFilter get(int index);

	boolean remove(int index);

	boolean remove(XmppFilter filter);

	List<XmppFilter> listAll();

}
