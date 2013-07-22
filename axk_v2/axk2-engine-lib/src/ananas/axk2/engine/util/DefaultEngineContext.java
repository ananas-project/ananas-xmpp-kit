package ananas.axk2.engine.util;

import java.net.InetSocketAddress;
import java.security.GeneralSecurityException;

import javax.net.ssl.SSLContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.xml.sax.XMLReader;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppStatus;
import ananas.axk2.engine.XEngine;
import ananas.axk2.engine.XEngineConnector;
import ananas.axk2.engine.XEngineContext;
import ananas.axk2.engine.XEngineListener;
import ananas.axk2.engine.XMLReaderProvider;
import ananas.lib.util.logging.Logger;

public class DefaultEngineContext implements XEngineContext {

	final static Logger log = Logger.Agent.getLogger();

	public XmppAccount _account;
	public DOMImplementation _dom_impl;
	public XEngineConnector _connector;
	public XEngineListener _listener;
	public XMLReaderProvider _xml_reader_provider;

	@Override
	public XmppAccount getAccount() {
		return this._account;
	}

	@Override
	public DOMImplementation getDOMImplementation() {
		DOMImplementation ret = this._dom_impl;
		if (ret == null) {
			this._dom_impl = ret = this.__newDomImpl();
		}
		return ret;
	}

	@Override
	public XEngineConnector getConnector() {
		XEngineConnector ret = this._connector;
		if (ret == null) {
			this._connector = ret = this.__newConnector();
		}
		return ret;
	}

	@Override
	public XEngineListener getListener() {
		XEngineListener ret = this._listener;
		if (ret == null) {
			this._listener = ret = this.__newListener();
		}
		return ret;
	}

	@Override
	public XMLReaderProvider getXMLReaderProvider() {
		XMLReaderProvider ret = this._xml_reader_provider;
		if (ret == null) {
			this._xml_reader_provider = ret = this.__newReaderProvider();
		}
		return ret;
	}

	private XMLReaderProvider __newReaderProvider() {
		return new XMLReaderProvider() {

			@Override
			public XMLReader newXMLReader() {
				try {
					SAXParser parser = SAXParserFactory.newInstance()
							.newSAXParser();
					return parser.getXMLReader();
				} catch (Exception e) {
					log.error(e);
					return null;
				}
			}
		};
	}

	private XEngineConnector __newConnector() {
		return new XEngineConnector() {

			@Override
			public SSLContext getSSLContext() throws GeneralSecurityException {
				return DefaultXEngineSSLContext.getInstance();
			}

			@Override
			public InetSocketAddress getAddressByAccount(XmppAccount account) {
				String host = account.host();
				int port = account.port();
				return new InetSocketAddress(host, port);
			}
		};
	}

	private XEngineListener __newListener() {
		return new XEngineListener() {

			@Override
			public void onStanza(XEngine engine, Element stanza) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPhaseChanged(XEngine engine, XmppStatus oldPhase,
					XmppStatus newPhase) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onBind(XEngine engine, XmppAddress jid) {
				// TODO Auto-generated method stub
				
			}

		};
	}

	private DOMImplementation __newDomImpl() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			return db.getDOMImplementation();
		} catch (ParserConfigurationException e) {
			log.error(e);
			return null;
		}
	}

}
