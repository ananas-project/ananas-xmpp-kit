package ananas.lib.axk.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface XSocketSource {

	InputStream getInput() throws IOException;

	OutputStream getOutput() throws IOException;

	Socket getSocket() throws IOException;

}
