package ananas.axk2.engine;

import java.security.GeneralSecurityException;

import javax.net.ssl.SSLContext;

public interface XEngineConnector {

	SSLContext getSSLContext() throws GeneralSecurityException;

}
