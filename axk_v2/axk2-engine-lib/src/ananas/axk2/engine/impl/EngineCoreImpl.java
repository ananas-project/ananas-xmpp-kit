package ananas.axk2.engine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

import ananas.axk2.engine.XEngineContext;
import ananas.axk2.engine.api.XDomBuilder;
import ananas.axk2.engine.api.XEncoding;
import ananas.axk2.engine.api.XEngineCore;
import ananas.axk2.engine.api.XEngineRuntimeContext;
import ananas.axk2.engine.api.XStanzaListener;
import ananas.axk2.engine.api.XStanzaProcessor;
import ananas.axk2.engine.api.XStanzaProcessorManager;
import ananas.lib.util.logging.Logger;

public class EngineCoreImpl implements XEngineCore {

	final static Logger log = Logger.Agent.getLogger();

	@Override
	public void run(XEngineRuntimeContext erc)
			throws UnsupportedEncodingException, IOException, SAXException,
			GeneralSecurityException {

		SocketAgent sa = erc.openSocket();
		InputStream in = sa.getInputStream();
		OutputStream out = sa.getOutputStream();
		erc.getSubConnection().setCurrentSocketAgent(sa);

		String domain = erc.getSubConnection().getParent().getParent()
				.getContext().getAccount().jid().domain();

		// send starting of stream
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<stream:stream");
		sb.append(" xmlns='jabber:client'");
		sb.append(" xmlns:stream='http://etherx.jabber.org/streams'");
		sb.append(" to='" + domain + "'");
		sb.append(" version='1.0'");
		sb.append(">");
		out.write(sb.toString().getBytes(XEncoding.theDefault));
		out.flush();

		// do RX loop

		XEngineContext context = erc.getSubConnection().getParent().getParent()
				.getContext();

		DOMImplementation impl = context.getDOMImplementation();
		XMLReader reader = context.getXMLReaderProvider().newXMLReader();
		XStanzaListener listener = new MyStanzaListener(erc);
		XDomBuilder builder = XDomBuilder.Factory.newInstance(impl, listener);

		reader.setFeature("http://xml.org/sax/features/namespaces", true);
		reader.setFeature("http://xml.org/sax/features/validation", false);
		reader.setFeature("http://apache.org/xml/features/validation/schema",
				false);

		reader.setContentHandler(builder.getContentHandler());
		reader.setErrorHandler(new MyErrorHandler());
		reader.parse(new InputSource(in));

	}

	class MyStanzaListener implements XStanzaListener {

		private final XEngineRuntimeContext _erc;
		private final XStanzaProcessorManager _stanzaPM;

		public MyStanzaListener(XEngineRuntimeContext erc) {
			this._erc = erc;
			this._stanzaPM = erc.getSubConnection().getStanzaProcessorManager();
		}

		@Override
		public void onStanza(Element element) {
			// String s = XEngineUtil.nodeToString(element);
			// log.info(this + ".onStanza : " + s);
			XStanzaProcessor proc = this._stanzaPM.getCurrentProcessor();
			proc.onStanza(_erc, element);
		}
	}

	class MyErrorHandler implements ErrorHandler {

		@Override
		public void error(SAXParseException arg0) throws SAXException {
			// TODO Auto-generated method stub

		}

		@Override
		public void fatalError(SAXParseException arg0) throws SAXException {
			// TODO Auto-generated method stub

		}

		@Override
		public void warning(SAXParseException arg0) throws SAXException {
			// TODO Auto-generated method stub

		}
	}

}
