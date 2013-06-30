package ananas.lib.axk.connection.util;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import ananas.lib.axk.connection.XMLReaderProvider;

public class DefaultXMLReaderProvider implements XMLReaderProvider {

	@Override
	public XMLReader createXMLReader() throws SAXException {
		String s = "ananas.lib.blueprint3.xmlparser.SAXParser";
		XMLReader rdr = XMLReaderFactory.createXMLReader(s);
		return rdr;
	}

}
