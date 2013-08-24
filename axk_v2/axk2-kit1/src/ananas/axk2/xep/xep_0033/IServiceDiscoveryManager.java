package ananas.axk2.xep.xep_0033;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppAddress;

public interface IServiceDiscoveryManager extends XmppAPI {

	void query(XmppAddress addr);

}
