package ananas.axk2.engine.test;

import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.DOMImplementation;
import org.xml.sax.XMLReader;

import ananas.axk2.core.DefaultAddress;
import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.engine.XEngine;
import ananas.axk2.engine.XEngineContext;
import ananas.axk2.engine.XEngineFactory;
import ananas.lib.util.PropertiesLoader;
import ananas.lib.util.logging.Logger;

public class TestAxkEngine {

	final static Logger log = Logger.Agent.getLogger();

	public static void main(String[] arg) {
		TestAxkEngine test = new TestAxkEngine();
		test.run();
	}

	private void run() {
		log.trace("start " + this);
		XEngineFactory factory = XEngineFactory.Agent.getDefault();
		XEngineContext context = new MyEngineContext();
		XEngine engine = factory.createEngine(context);
		engine.start();
	}

	static class MyEngineContext implements XEngineContext {

		private final XmppAccount _account = new MyAccount();

		@Override
		public XmppAccount getAccount() {
			return this._account;
		}

		@Override
		public DOMImplementation getDOMImplementation() {
			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				return db.getDOMImplementation();
			} catch (ParserConfigurationException e) {
				log.error(e);
				return null;
			}
		}

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
	}

	static class MyAccount implements XmppAccount {

		private final Properties _prop;

		public MyAccount() {

			PropertiesLoader propLoader = PropertiesLoader.Util.loaderFor(this,
					"account.conf");
			this._prop = propLoader.load();

		}

		@Override
		public XmppAddress jid() {
			String s = this._str("jid");
			return new DefaultAddress(s);
		}

		private String _str(String key) {
			return this._prop.getProperty(key);
		}

		@Override
		public String resource() {
			return this._str("resource");
		}

		@Override
		public String password() {
			return this._str("password");
		}

		@Override
		public String host() {
			return this._str("host");
		}

		@Override
		public int port() {
			String s = this._str("port");
			return Integer.parseInt(s);
		}

		@Override
		public boolean useSSL() {
			String s = this._str("use_ssl");
			return Boolean.parseBoolean(s);
		}

		@Override
		public boolean ignoreTLSError() {
			String s = this._str("ignore_tls_error");
			return Boolean.parseBoolean(s);
		}
	}
}
