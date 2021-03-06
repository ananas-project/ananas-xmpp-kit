package ananas.axk2.engine.api;

import java.io.IOException;
import java.io.OutputStream;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.engine.impl.SASLProcessorManager;
import ananas.axk2.engine.impl.SocketAgent;

public interface XSubConnection extends XLifeCycle {

	XThreadRuntime getParent();

	void run();

	boolean hasOnline();

	XStanzaProcessorManager getStanzaProcessorManager();

	SASLProcessorManager getSASLProcessorManager();

	void send_sync(String data) throws IOException;

	void setCurrentSocketAgent(SocketAgent sa);

	SocketAgent getCurrentSocketAgent();

	OutputStream getOnlineOutput();

	void setOnlineOutput(OutputStream out);

	XmppAccount getFinalAccount();

}
