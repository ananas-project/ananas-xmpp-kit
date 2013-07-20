package ananas.axk2.engine.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface XEngineRuntimeContext {

	SocketAgent openSocket() throws IOException, GeneralSecurityException;

	XSubConnection getSubConnection();

}
