package ananas.axk2.engine.api;

import java.io.IOException;

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

}
