package ananas.lib.axk.engine.impl;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import ananas.lib.axk.engine.XContext;

class MyBuilderImpl implements XBuilder {

	private final ContentHandler m_ch;
	private final ErrorHandler m_eh;

	public MyBuilderImpl(XContext context) {
		this.m_ch = new myContentHandler(context);
		this.m_eh = new myErrorHandler(context);
	}

	@Override
	public ContentHandler getContentHandler() {
		return this.m_ch;
	}

	@Override
	public ErrorHandler getErrorHandler() {
		return this.m_eh;
	}

	class myContentHandler implements ContentHandler {

		public myContentHandler(XContext context) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void characters(char[] arg0, int arg1, int arg2)
				throws SAXException {
			// TODO Auto-generated method stub

		}

		@Override
		public void endDocument() throws SAXException {
			// TODO Auto-generated method stub

		}

		@Override
		public void endElement(String arg0, String arg1, String arg2)
				throws SAXException {
			// TODO Auto-generated method stub

		}

		@Override
		public void endPrefixMapping(String arg0) throws SAXException {
			// TODO Auto-generated method stub

		}

		@Override
		public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
				throws SAXException {
			// TODO Auto-generated method stub

		}

		@Override
		public void processingInstruction(String arg0, String arg1)
				throws SAXException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setDocumentLocator(Locator arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void skippedEntity(String arg0) throws SAXException {
			// TODO Auto-generated method stub

		}

		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub

		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes atts) throws SAXException {

			StringBuilder sb = new StringBuilder();
			sb.append("<" + qName);
			int len = atts.getLength();
			for (int i = 0; i < len; i++) {
				String k, v;
				k = atts.getQName(i);
				v = atts.getValue(i);
				sb.append(" " + k + "='" + v + "'");
			}
			sb.append(">");
			System.out.println(this + ".startElement:" + sb.toString());

		}

		@Override
		public void startPrefixMapping(String arg0, String arg1)
				throws SAXException {
			// TODO Auto-generated method stub

		}
	}

	class myErrorHandler implements ErrorHandler {

		public myErrorHandler(XContext context) {
			// TODO Auto-generated constructor stub
		}

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
