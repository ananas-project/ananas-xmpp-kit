package ananas.lib.axk.engine.impl;

import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;

import ananas.lib.axk.engine.XContext;

interface MyBuilder {

	class Factory {

		public static MyBuilder createBuilder(XContext context) {
			return new MyBuilderImpl(context);
		}

	}

	ContentHandler getContentHandler();

	ErrorHandler getErrorHandler();

}
