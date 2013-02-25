package ananas.lib.axk;

import ananas.lib.blueprint.core.lang.BPEnvironment;

public interface XmppEnvironment {

	BPEnvironment getBPEnvironment();

	XmppClientFactory getClientFactory();

	void shutdown();

	boolean isAlive();

}
