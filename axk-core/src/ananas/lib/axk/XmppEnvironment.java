package ananas.lib.axk;

import ananas.lib.axk.security.AXKSecurityManager;
import ananas.lib.blueprint.core.lang.BPEnvironment;

public interface XmppEnvironment {

	void shutdown();

	boolean isAlive();

	BPEnvironment getBPEnvironment();

	XmppClientFactory getClientFactory();

	AXKSecurityManager getSecurityManager();

}
