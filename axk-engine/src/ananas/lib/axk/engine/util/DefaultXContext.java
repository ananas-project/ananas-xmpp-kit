package ananas.lib.axk.engine.util;

import javax.net.ssl.SSLSocketFactory;

import org.w3c.dom.DOMImplementation;

import ananas.lib.axk.engine.XAccount;
import ananas.lib.axk.engine.XContext;
import ananas.lib.axk.engine.XEngineFactory;
import ananas.lib.axk.engine.XEngineListener;
import ananas.lib.axk.engine.XMLReaderProvider;
import ananas.lib.axk.engine.XSocketContext;

public class DefaultXContext implements XContext {

	public XAccount m_account;
	public XSocketContext m_socketContext;
	public XEngineListener m_engineListener;
	public XMLReaderProvider m_xmlReaderProvider;
	public DOMImplementation m_domImpl;
	public SSLSocketFactory m_sslSocketFactory;
	public XEngineFactory m_engineFactory;

	public DefaultXContext() {
	}

	@Override
	public XEngineFactory getEngineFactory() {
		return this.m_engineFactory;
	}

	@Override
	public XSocketContext getSocketContext() {
		return this.m_socketContext;
	}

	@Override
	public XAccount getAccount() {
		return this.m_account;
	}

	@Override
	public XEngineListener getEngineListener() {
		return this.m_engineListener;
	}

	@Override
	public DOMImplementation getDOMImplementation() {
		return this.m_domImpl;
	}

	@Override
	public XMLReaderProvider getXMLReaderProvider() {
		return this.m_xmlReaderProvider;
	}

	@Override
	public SSLSocketFactory getSSLSocketFactory() {
		return this.m_sslSocketFactory;
	}

}
