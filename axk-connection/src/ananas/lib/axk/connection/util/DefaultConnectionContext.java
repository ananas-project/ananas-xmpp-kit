package ananas.lib.axk.connection.util;

import org.w3c.dom.DOMImplementation;

import ananas.lib.axk.connection.XAccount;
import ananas.lib.axk.connection.XConnectionContext;
import ananas.lib.axk.connection.XMLReaderProvider;
import ananas.lib.axk.connection.dom.TheDOMImplementation;

public class DefaultConnectionContext implements XConnectionContext {

	private XAccount m_account;
	private XMLReaderProvider m_saxProvider;
	private DOMImplementation m_domImpl;

	@Override
	public DOMImplementation getDOMImplementation() {
		DOMImplementation impl = this.m_domImpl;
		if (impl == null) {
			impl = new TheDOMImplementation();
			this.m_domImpl = impl;
		}
		return impl;
	}

	@Override
	public XAccount getAccount() {
		return this.m_account;
	}

	@Override
	public XMLReaderProvider getXMLReaderProvider() {
		XMLReaderProvider provider = this.m_saxProvider;
		if (provider == null) {
			provider = new DefaultXMLReaderProvider();
			this.m_saxProvider = provider;
		}
		return provider;
	}

	public void setAccount(XAccount account) {
		this.m_account = account;
	}

}
