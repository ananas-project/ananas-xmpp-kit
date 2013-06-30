package ananas.lib.axk.connection.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import ananas.lib.axk.connection.XConnection;
import ananas.lib.axk.connection.XConnectionContext;
import ananas.lib.axk.connection.XDomListener;
import ananas.lib.axk.connection.XSocketSource;

public class MyConnection implements XConnection {

	private final XConnectionContext m_context;
	private XDomListener m_domListener;

	public MyConnection(XConnectionContext context) {
		this.m_context = context;
	}

	@Override
	public void setDomListener(XDomListener listener) {
		this.m_domListener = listener;
	}

	@Override
	public void parse(XSocketSource source) throws IOException, SAXException {

		OutputStream out = source.getOutput();
		{
			// output
			StringBuilder sb = new StringBuilder();
			sb.append("<stream:stream");
			sb.append(" xmlns='jabber:client'");
			sb.append(" xmlns:stream='http://etherx.jabber.org/streams'");
			// sb.append(" to='...'");
			sb.append(">");
			out.write(sb.toString().getBytes("UTF-8"));
		}
		InputStream in = source.getInput();
		{
			// input
			Document doc = this.m_context.getDOMImplementation()
					.createDocument(null, null, null);
			XMLReader reader = this.m_context.getXMLReaderProvider()
					.createXMLReader();

			doc = new MyDocumentWrapper(doc);

			MyBuilder builder = new MyBuilder(doc);

			reader.setContentHandler(builder.getContentHandler());
			reader.setErrorHandler(builder.getErrorHandler());
			reader.parse(new InputSource(in));
		}
	}

}
