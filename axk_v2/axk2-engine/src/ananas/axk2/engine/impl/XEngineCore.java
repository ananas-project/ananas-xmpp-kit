package ananas.axk2.engine.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.xml.sax.SAXException;

public interface XEngineCore {

	void run(XEngineRuntimeContext erc) throws UnsupportedEncodingException,
			IOException, SAXException;

}
