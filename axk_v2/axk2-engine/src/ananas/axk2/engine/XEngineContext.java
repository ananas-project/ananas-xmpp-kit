package ananas.axk2.engine;

import org.w3c.dom.DOMImplementation;

import ananas.axk2.core.XmppAccount;

public interface XEngineContext {

	XmppAccount getAccount();

	DOMImplementation getDOMImplementation();

	// XMLReader newXMLReader();
	XMLReaderProvider getXMLReaderProvider();

	XEngineConnector getConnector();

	XEngineListener getListener();

}
