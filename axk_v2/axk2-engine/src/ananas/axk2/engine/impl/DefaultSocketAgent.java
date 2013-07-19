package ananas.axk2.engine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DefaultSocketAgent implements SocketAgent {

	private final Socket _sock;
	private OutputStream _out;
	private InputStream _in;

	public DefaultSocketAgent(Socket sock) {
		this._sock = sock;
	}

	public DefaultSocketAgent(Socket sock, InputStream in, OutputStream out) {
		this._sock = sock;
		this._in = in;
		this._out = out;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		InputStream in = this._in;
		if (in == null) {
			this._in = in = this._sock.getInputStream();
		}
		return in;
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		OutputStream out = this._out;
		if (out == null) {
			this._out = out = this._sock.getOutputStream();
		}
		return out;
	}

	@Override
	public Socket getSocket() {
		return this._sock;
	}

}
