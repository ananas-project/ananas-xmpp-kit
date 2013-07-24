package ananas.axk2.core.command;

import ananas.axk2.core.XmppConnection;

public interface StanzaCommandFactory extends XmppCommandFactory {

	StanzaCommand create(XmppConnection conn);

}
