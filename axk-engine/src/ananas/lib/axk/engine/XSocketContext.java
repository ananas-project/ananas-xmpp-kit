package ananas.lib.axk.engine;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface XSocketContext {

	Socket getSocket();

	InputStream getInput();

	OutputStream getOutput();
}
