package ananas.axk2.core.api;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.event.StanzaEvent;

public interface IEventAgent extends XmppAPI {

	StanzaEvent newStanzaEvent(XmppConnection conn);

}
