package ananas.axk2.engine.api;

import java.io.IOException;
import java.security.GeneralSecurityException;

import ananas.axk2.engine.impl.SocketAgent;

public interface XEngineRuntimeContext {

	SocketAgent openSocket() throws IOException, GeneralSecurityException;

	XSubConnection getSubConnection();

}
