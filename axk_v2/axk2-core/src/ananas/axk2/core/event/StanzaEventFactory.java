package ananas.axk2.core.event;

import ananas.axk2.core.XmppConnection;

public interface StanzaEventFactory extends XmppEventFactory {

	StanzaEvent create(XmppConnection conn);

}
