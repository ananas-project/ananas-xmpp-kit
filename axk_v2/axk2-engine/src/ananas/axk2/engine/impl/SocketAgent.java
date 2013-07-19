package ananas.axk2.engine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface SocketAgent {

	InputStream getInputStream() throws IOException;

	OutputStream getOutputStream() throws IOException;

	Socket getSocket();
}
