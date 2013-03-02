package ananas.lib.axk.api;

import ananas.lib.axk.XmppClientExAPI;

public interface IExConnectionSync extends XmppClientExAPI {

	boolean syncSendStanza(String stanza);

	boolean syncClose();

}
