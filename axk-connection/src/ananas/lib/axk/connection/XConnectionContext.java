package ananas.lib.axk.connection;

import org.w3c.dom.DOMImplementation;

public interface XConnectionContext {

	DOMImplementation getDOMImplementation();

	XMLReaderProvider getXMLReaderProvider();

	XAccount getAccount();

}
