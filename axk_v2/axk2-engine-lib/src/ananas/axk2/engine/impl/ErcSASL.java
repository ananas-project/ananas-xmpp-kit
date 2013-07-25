package ananas.axk2.engine.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;

import ananas.axk2.engine.api.XEngineRuntimeContext;
import ananas.axk2.engine.api.XSubConnection;

public class ErcSASL implements XEngineRuntimeContext {

	private XSubConnection _sub_conn;
	private SocketAgent _sock;
	private XEngineRuntimeContext _parent;
	private String _name;

	public ErcSASL(XEngineRuntimeContext erc) {
		this._parent = erc;
		this._sub_conn = erc.getSubConnection();
		// this._sock=erc.openSocket() ;
		this._name = this.getClass().getSimpleName();
	}

	@Override
	public SocketAgent openSocket() throws IOException,
			GeneralSecurityException {
		SocketAgent sock = this._sock;
		if (sock == null) {
			this._sock = sock = this._parent.openSocket();
		}
		return sock;
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
