package ananas.axk2.stringio;

import ananas.axk2.core.XmppAPI;

public interface IStringIOAgent extends XmppAPI {

	void bindIO(IStringIO io);

	IStringIO getIO();

}
