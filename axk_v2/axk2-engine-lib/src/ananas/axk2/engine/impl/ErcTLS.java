package ananas.axk2.engine.impl;

import java.io.IOException;
import java.net.Socket;
import java.security.GeneralSecurityException;

import javax.net.ssl.SSLSocketFactory;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.engine.XEngineConnector;
import ananas.axk2.engine.XEngineContext;
import ananas.axk2.engine.api.XEngineRuntimeContext;
import ananas.axk2.engine.api.XSubConnection;

public class ErcTLS implements XEngineRuntimeContext {

	private final XSubConnection _sub_conn;
	private SocketAgent _sock_agent;
	private final XEngineRuntimeContext _parent;
	private String _name;

	public ErcTLS(XEngineRuntimeContext erc) {
		this._sub_conn = erc.getSubConnection();
		this._parent = erc;
		this._name = this.getClass().getSimpleName();
	}

	@Override
	public SocketAgent openSocket() throws IOException,
			GeneralSecurityException {
		SocketAgent sa = this._sock_agent;
		if (sa == null) {
			Socket sock = this.__openTLS();
			sa = new DefaultSocketAgent(sock);
			this._sock_agent = sa;
		}
		return sa;
	}

	private Socket __openTLS() throws IOException, GeneralSecurityException {

		final SocketAgent sockOld = this._parent.openSocket();
		final XSubConnection subcon = this._parent.getSubConnection();
		final XEngineContext context = subcon.getParent().getParent()
				.getContext();
		final XEngineConnector connector = context.getConnector();
		final SSLSocketFactory sockFactory = connector.getSSLContext()
				.getSocketFactory();
		final XmppAccount account = subcon.getFinalAccount();
		final String host = account.host();
		final int port = account.port();
		final Socket sockNew = sockFactory.createSocket(sockOld.getSocket(),
				host, port, true);
		return sockNew;
	}

	@Override
	public XSubConnection getSubConnection() {
		return this._sub_conn;
	}

	@Override
	public XEngineRuntimeContext getParent() {
		return this._parent;
	}

	@Override
	public String getName() {
		return this._name;
	}

	@Override
	public String getFullName() {
		return (this.getParent().getFullName() + "." + this.getName());
	}

}
