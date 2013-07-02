package ananas.lib.axk.engine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import ananas.lib.axk.engine.XContext;
import ananas.lib.axk.engine.XEngine;
import ananas.lib.axk.engine.XSocketContext;

final class XEngineImpl implements XEngine {

	@Override
	public void run(XContext context) throws IOException, SAXException {

		XSocketContext sockCont = context.getSocketContext();
		InputStream in = sockCont.getInput();
		OutputStream out = sockCont.getOutput();

		// out
		StringBuilder sb = new StringBuilder();
		{
			sb.append("<stream:stream");
			sb.append(" xmlns='...'");
			sb.append(" version='1.0'");
			sb.append(">");
		}
		out.write(sb.toString().getBytes("UTF-8"));
		out.flush();

		// in
		XBuilder builder = XBuilder.Factory.createBuilder(context);
		XMLReader reader = context.getXMLReaderProvider().newReader();
		reader.setContentHandler(builder.getContentHandler());
		reader.setErrorHandler(builder.getErrorHandler());
		reader.parse(new InputSource(in));

	}

}
