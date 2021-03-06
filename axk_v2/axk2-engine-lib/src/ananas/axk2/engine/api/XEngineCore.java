package ananas.axk2.engine.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import org.xml.sax.SAXException;

import ananas.axk2.engine.impl.EngineCoreImpl;

public interface XEngineCore {

	void run(XEngineRuntimeContext erc) throws UnsupportedEncodingException,
			IOException, SAXException, GeneralSecurityException;

	public static class Factory {
		public static XEngineCore newInstance() {
			XEngineCore core = new EngineCoreImpl();
			return core;
		}
	}
}
