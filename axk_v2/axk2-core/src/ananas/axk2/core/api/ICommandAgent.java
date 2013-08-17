package ananas.axk2.core.api;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.command.StanzaCommand;

public interface ICommandAgent extends XmppAPI {

	StanzaCommand newStanzaCommand(XmppConnection conn);

}
