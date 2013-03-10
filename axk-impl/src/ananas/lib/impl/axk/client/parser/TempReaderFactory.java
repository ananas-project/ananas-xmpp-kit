package ananas.lib.impl.axk.client.parser;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import ananas.lib.blueprint3.core.util.BPXMLReaderFactory;

public class TempReaderFactory implements BPXMLReaderFactory {

	@Override
	public XMLReader newReader() throws SAXException {

		String cls = "ananas.lib.blueprint.xmlparser.SAXParser";
		return XMLReaderFactory.createXMLReader(cls);
	}

}
