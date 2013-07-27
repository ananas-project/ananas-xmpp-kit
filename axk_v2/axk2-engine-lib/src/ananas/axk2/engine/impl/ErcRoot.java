package ananas.axk2.engine.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.engine.XEngineContext;
import ananas.axk2.engine.api.XEngineRuntimeContext;
import ananas.axk2.engine.api.XSubConnection;

public class ErcRoot implements XEngineRuntimeContext {

	private final XSubConnection _sub_conn;
	private SocketAgent _sock_agent;

	public ErcRoot(XSubConnection subConn) {
		this._sub_conn = subConn;
	}

	@Override
	public SocketAgent openSocket() throws IOException,
			GeneralSecurityException {
		SocketAgent sa = this._sock_agent;
		if (sa == null) {
			final XEngineContext context = this._sub_conn.getParent()
					.getParent().getContext();
			final XmppAccount account = this._sub_conn.getFinalAccount();
			final Socket sock;
			if (account.useSSL()) {
				SSLSocketFactory factory = context.getConnector()
						.getSSLContext().getSocketFactory();
				sock = factory.createSocket();
			} else {
				sock = SocketFactory.getDefault().createSocket();
			}
			String host = account.host();
			int port = account.port();
			sock.connect(new InetSocketAddress(host, port));
			sa = new DefaultSocketAgent(sock);
			this._sock_agent = sa;
		}
		return sa;
	}

	@Override
	public XSubConnection getSubConnection() {
		return this._sub_conn;
	}

	@Override
	public XEngineRuntimeContext getParent() {
		return null;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String getFullName() {
		return this.getName();
	}

}
