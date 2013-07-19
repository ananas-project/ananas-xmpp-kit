package ananas.axk2.engine.impl;

import java.util.Stack;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import ananas.lib.util.logging.Logger;

public class DomBuilderImpl implements XDomBuilder, ContentHandler {

	final static Logger log = Logger.Agent.getLogger();

	private final DOMImplementation _dom_impl;
	private final XStanzaListener _listener;
	private Document _doc;
	private final Stack<Element> _stack;

	public DomBuilderImpl(DOMImplementation impl, XStanzaListener listener) {
		this._stack = new Stack<Element>();
		this._dom_impl = impl;
		this._listener = listener;
	}

	@Override
	public ContentHandler getContentHandler() {
		return this;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		int i1, i2;
		i1 = start;
		i2 = start + length - 1;
		for (; i1 <= i2;) {
			char c;
			c = ch[i1];
			final boolean is1sp = (c == ' ' || c == 0x09 || c == 0x0a || c == 0x0d);
			c = ch[i2];
			final boolean is2sp = (c == ' ' || c == 0x09 || c == 0x0a || c == 0x0d);
			if (is1sp) {
				i1++;
				if (is2sp) {
					i2--;
				}
			} else {
				if (is2sp) {
					i2--;
				} else {
					break;
				}
			}
		}
		int len = (i2 - i1) + 1;
		if (len > 0) {
			String s = new String(ch, i1, len);
			// log.trace(this + ".characters() : " + s);
			if (_stack.size() > 0) {
				Element node = _stack.peek();
				Text txt = node.getOwnerDocument().createTextNode(s);
				node.appendChild(txt);
			}
		}
	}

	@Override
	public void endDocument() throws SAXException {
		this._stack.clear();
		this._doc = null;
	}

	final static String ns_uri_stream = "http://etherx.jabber.org/streams";

	@Override
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {

		Element child = _stack.pop();
		// log.info(this + ".endElement() : " + child.getNamespaceURI() + "#"
		// + child.getLocalName());

		if (_stack.size() > 0) {
			Element parent = _stack.peek();
			if (parent.getLocalName().equals("stream"))
				if (parent.getNamespaceURI().equals(ns_uri_stream)) {
					this._listener.onStanza(child);
					return;
				}
			// else
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

		// log.trace(this + ".startDocument()");

		Document doc = this._dom_impl.createDocument(null, null, null);
		this._doc = doc;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {

		// log.trace(this + ".startElement() : " + qName);

		final Document doc = _doc;
		Element element = doc.createElementNS(uri, qName);
		this._stack.push(element);

		int len = atts.getLength();
		for (int i = 0; i < len; i++) {
			String attrURI = atts.getURI(i);
			String attrQName = atts.getQName(i);
			String attrValue = atts.getValue(i);
			// Attr attr = doc.createAttributeNS(attrURI, attrQName);
			if (attrQName.startsWith("xmlns")) {
				if (attrQName.equals("xmlns")) {
					continue;
				} else if (attrQName.startsWith("xmlns:")) {
					continue;
				}
			}
			element.setAttributeNS(attrURI, attrQName, attrValue);
		}
	}

	@Override
	public void startPrefixMapping(String arg0, String arg1)
			throws SAXException {
		// TODO Auto-generated method stub

	}

}
