package ananas.axk2.core.util;

import org.w3c.dom.Element;

import ananas.axk2.core.XmppAddress;

public interface StanzaContext {

	// base getter

	Element getElement();

	Object getObject();

	String getString();

	// base setter

	void setElement(Element element);

	void setString(String string);

	void setObject(Object object);

	// advanced getter

	String get_id();

	XmppAddress get_from();

	XmppAddress get_to();

	String get_type();

	String get_xml_lang();

}
