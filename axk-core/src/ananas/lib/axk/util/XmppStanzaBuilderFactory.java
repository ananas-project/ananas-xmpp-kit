package ananas.lib.axk.util;

import ananas.lib.axk.XmppAccount;
import ananas.lib.axk.XmppClient;

public interface XmppStanzaBuilderFactory {

	XmppStanzaBuilder newInstance();

	XmppStanzaBuilder newInstance(XmppClient client);

	XmppStanzaBuilder newInstance(XmppAccount account);

}
