package ananas.axk2.core;

import java.util.List;

public interface XmppFilterManager {

	boolean insertFilter(XmppFilter filter, int index);

	boolean removeFilter(int index);

	boolean removeFilter(XmppFilter filter);

	List<XmppFilter> listFilters();

}
