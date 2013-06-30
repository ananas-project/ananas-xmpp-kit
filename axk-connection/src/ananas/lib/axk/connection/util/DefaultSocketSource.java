package ananas.lib.axk.connection.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import ananas.lib.axk.connection.XAccount;
import ananas.lib.axk.connection.XConnectionContext;
import ananas.lib.axk.connection.XSocketSource;

public class DefaultSocketSource implements XSocketSource {

	private final XConnectionContext m_context;
	private Socket m_socket;

	public DefaultSocketSource(XConnectionContext context) {
		this.m_context = context;
	}

	@Override
	public InputStream getInput() throws IOException {
		return this.getSocket().getInputStream();
	}

	@Override
	public OutputStream getOutput() throws IOException {
		return this.getSocket().getOutputStream();
	}

	@Override
	public Socket getSocket() throws IOException {
		Socket sock = this.m_socket;
		if (sock == null) {
			XAccount account = this.m_context.getAccount();
			if (account.isUseSSL()) {
				sock = SSLSocketFactory.getDefault().createSocket();
			} else {
				sock = SocketFactory.getDefault().createSocket();
			}
			String host = account.getHost();
			int port = account.getPort();
			SocketAddress addr = new InetSocketAddress(host, port);
			sock.connect(addr);
			this.m_socket = sock;
		}
		return sock;
	}

}
