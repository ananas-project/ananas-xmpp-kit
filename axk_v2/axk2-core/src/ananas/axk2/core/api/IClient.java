package ananas.axk2.core.api;

import ananas.axk2.core.XmppAPI;

public interface IClient extends XmppAPI {

	void connect();

	void disconnect();

}
