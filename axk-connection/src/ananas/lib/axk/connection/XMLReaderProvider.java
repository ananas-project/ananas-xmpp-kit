package ananas.lib.axk.connection;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public interface XMLReaderProvider {

	XMLReader createXMLReader() throws SAXException;
}
