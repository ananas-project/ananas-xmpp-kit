package ananas.axk2.engine.impl;

import java.io.IOException;

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
