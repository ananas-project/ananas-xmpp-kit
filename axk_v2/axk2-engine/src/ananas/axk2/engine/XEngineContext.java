package ananas.axk2.engine;

import org.w3c.dom.DOMImplementation;
import org.xml.sax.XMLReader;

import ananas.axk2.core.XmppAccount;

public interface XEngineContext {

	XmppAccount getAccount();

	DOMImplementation getDOMImplementation();

	XMLReader newXMLReader();

}
