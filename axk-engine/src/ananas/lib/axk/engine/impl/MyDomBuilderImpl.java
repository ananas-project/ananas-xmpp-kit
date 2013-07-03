package ananas.lib.axk.engine.impl;

import java.util.Stack;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import ananas.lib.axk.engine.XContext;

class MyDomBuilderImpl implements XBuilder {

	private final ContentHandler m_ch;
	private final ErrorHandler m_eh;

	public MyDomBuilderImpl(XContext context) {
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

		private final XContext mContext;
		private Document mDoc;
		private final Stack<Element> mElementStack;

		public myContentHandler(XContext context) {
			this.mContext = context;
			this.mElementStack = new Stack<Element>();
		}

		@Override
		public void characters(char[] chs, int offset, int length)
				throws SAXException {

			String s = new String(chs, offset, length);
			System.out.println(this + ".text:        " + s);

			// build dom
			if (this.mElementStack.size() > 0) {
				Element element = this.mElementStack.peek();
				Text text = this.mDoc.createTextNode(s);
				element.appendChild(text);
			}
		}

		@Override
		public void endDocument() throws SAXException {
			this.mElementStack.clear();
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {

			System.out.println(this + ".element: </" + qName + ">");

			// build dom
			Element child = this.mElementStack.pop();
			if (this.mElementStack.size() > 0) {
				Element parent = this.mElementStack.peek();
				parent.appendChild(child);
			} else {
				this.mDoc.appendChild(child);
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
			String namespaceURI = "";
			String qualifiedName = "";
			DocumentType doctype = null;
			Document doc = this.mContext.getDOMImplementation().createDocument(
					namespaceURI, qualifiedName, doctype);
			this.mDoc = doc;
			this.mElementStack.clear();
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
			System.out.println(this + ".element: " + sb.toString());

			// build dom
			Element element = this.mDoc.createElementNS(uri, qName);
			len = atts.getLength();
			for (int i = 0; i < len; i++) {
				String attrURI, attrQName, attrValue;
				attrURI = atts.getURI(i);
				attrQName = atts.getQName(i);
				attrValue = atts.getValue(i);
				element.setAttributeNS(attrURI, attrQName, attrValue);
			}
			this.mElementStack.push(element);
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
