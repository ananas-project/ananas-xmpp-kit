package ananas.lib.axk.connection.impl;

import java.util.Stack;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MyBuilder {

	private final Document m_doc;

	public MyBuilder(Document doc) {
		this.m_doc = doc;
	}

	public ContentHandler getContentHandler() {
		return this.m_contentHandler;
	}

	public ErrorHandler getErrorHandler() {
		return this.m_errorHandler;
	}

	private final ContentHandler m_contentHandler = new ContentHandler() {

		private final Stack<Element> m_elementStask = new Stack<Element>();

		private Document getDocument() {
			return MyBuilder.this.m_doc;
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {

			System.out.println(this + ".characters");

			if (this.m_elementStask.size() > 0) {
				Element element = this.m_elementStask.peek();
				Document doc = element.getOwnerDocument();
				String s = new String(ch, start, length);
				Text text = doc.createTextNode(s);
				element.appendChild(text);
			}
		}

		@Override
		public void endDocument() throws SAXException {
			System.out.println(this + ".endDocument");
			this.m_elementStask.clear();
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {

			System.out.println(this + ".endElement:" + qName);

			Element child = this.m_elementStask.pop();
			if (!this.m_elementStask.empty()) {
				Element parent = this.m_elementStask.peek();
				parent.appendChild(child);
			}
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
			System.out.println(this + ".startDocument");
			this.m_elementStask.clear();
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes atts) throws SAXException {

			System.out.println(this + ".startElement:" + qName);

			Document doc = this.getDocument();
			Element element = doc.createElementNS(uri, qName);
			int len = atts.getLength();
			for (int i = 0; i < len; i++) {
				String namespaceURI = atts.getURI(i);
				String qualifiedName = atts.getQName(i);
				String value = atts.getValue(i);
				element.setAttributeNS(namespaceURI, qualifiedName, value);
			}
			m_elementStask.push(element);
		}

		@Override
		public void startPrefixMapping(String arg0, String arg1)
				throws SAXException {
			// TODO Auto-generated method stub

		}
	};

	private final ErrorHandler m_errorHandler = new ErrorHandler() {

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
	};

}
