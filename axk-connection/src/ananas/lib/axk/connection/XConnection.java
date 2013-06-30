package ananas.lib.axk.connection;

import java.io.IOException;

import org.xml.sax.SAXException;

public interface XConnection {

	void setDomListener(XDomListener listener);

	void parse(XSocketSource source) throws IOException, SAXException;

}
