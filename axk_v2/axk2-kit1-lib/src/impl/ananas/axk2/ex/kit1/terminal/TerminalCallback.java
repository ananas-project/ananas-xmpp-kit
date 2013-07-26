package impl.ananas.axk2.ex.kit1.terminal;

import ananas.axk2.core.XmppConnection;

public interface TerminalCallback {

	boolean isNeedExit();

	XmppConnection getConnection();

}
