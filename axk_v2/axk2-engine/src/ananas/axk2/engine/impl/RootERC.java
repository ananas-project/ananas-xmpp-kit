package ananas.axk2.engine.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import javax.net.SocketFactory;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.engine.XEngineContext;

public class RootERC implements XEngineRuntimeContext {

	private final XSubConnection _sub_conn;
	private SocketAgent _sock_agent;

	public RootERC(XSubConnection subConn) {
		this._sub_conn = subConn;
	}

	@Override
	public SocketAgent openSocket() throws IOException {
		SocketAgent sa = this._sock_agent;
		if (sa == null) {
			Socket sock = null;

			XEngineContext context = this._sub_conn.getParent().getParent()
					.getContext();

			XmppAccount account = context.getAccount();
			if (account.useSSL()) {
				// TODO open ssl socket
				throw new RuntimeException("no impl");
			} else {
				sock = SocketFactory.getDefault().createSocket();
			}
			String host = account.host();
			int port = account.port();
			SocketAddress addr = new InetSocketAddress(host, port);
			sock.connect(addr);
			sa = new DefaultSocketAgent(sock);
			this._sock_agent = sa;
		}
		return sa;
	}

	@Override
	public XSubConnection getSubConnection() {
		return this._sub_conn;
	}

}
