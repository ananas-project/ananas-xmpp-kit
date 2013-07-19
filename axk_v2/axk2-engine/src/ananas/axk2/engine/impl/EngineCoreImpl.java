package ananas.axk2.engine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

import ananas.axk2.engine.XEngineContext;
import ananas.axk2.engine.impl.builder.XDomBuilder;
import ananas.axk2.engine.util.XEngineUtil;
import ananas.lib.util.logging.Logger;

public class EngineCoreImpl implements XEngineCore {

	final static Logger log = Logger.Agent.getLogger();

	@Override
	public void run(XEngineRuntimeContext erc)
			throws UnsupportedEncodingException, IOException, SAXException {

		SocketAgent sa = erc.openSocket();
		InputStream in = sa.getInputStream();
		OutputStream out = sa.getOutputStream();

		// send starting of stream
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<stream:stream");
		sb.append(" xmlns='jabber:client'");
		sb.append(" xmlns:stream='http://etherx.jabber.org/streams'");
		sb.append(" version='1.0'");
		sb.append(">");
		out.write(sb.toString().getBytes(XEncoding.theDefault));
		out.flush();

		// do RX loop

		XEngineContext context = erc.getSubConnection().getParent().getParent()
				.getContext();

		DOMImplementation impl = context.getDOMImplementation();
		XMLReader reader = context.newXMLReader();
		XStanzaListener listener = new MyStanzaListener(erc);
		XDomBuilder builder = XDomBuilder.Factory.newInstance(impl, listener);

		String feature_ns = "http://xml.org/sax/features/namespaces";
		reader.setFeature(feature_ns, true);

		reader.setContentHandler(builder.getContentHandler());
		reader.setErrorHandler(new MyErrorHandler());
		reader.parse(new InputSource(in));

	}

	class MyStanzaListener implements XStanzaListener {

		public MyStanzaListener(XEngineRuntimeContext erc) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onStanza(Element element) {
			String s = XEngineUtil.nodeToString(element);
			log.info(this + ".onStanza : " + s);
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
