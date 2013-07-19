package ananas.axk2.engine.impl;

import java.io.IOException;

public interface XEngineRuntimeContext {

	SocketAgent openSocket() throws IOException;

	XSubConnection getSubConnection();

}
