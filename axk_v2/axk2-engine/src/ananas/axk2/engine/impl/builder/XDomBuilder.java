package ananas.axk2.engine.impl.builder;

import org.w3c.dom.DOMImplementation;
import org.xml.sax.ContentHandler;

import ananas.axk2.engine.impl.XStanzaListener;

public interface XDomBuilder {

	class Factory {
		public static XDomBuilder newInstance(DOMImplementation impl,
				XStanzaListener listener) {
			return new DomBuilderImpl(impl, listener);
		}
	}

	ContentHandler getContentHandler();
}
