package ananas.axk2.core.api;

import ananas.axk2.core.XmppAPI;

public interface XapiClient extends XmppAPI {

	void reset();

	void connect();

	void disconnect();

	void close();

}
