package ananas.lib.axk.connection;

import java.net.URI;

public interface XAccount {

	URI getJID();

	String getHost();

	int getPort();

	boolean isUseSSL();

}
