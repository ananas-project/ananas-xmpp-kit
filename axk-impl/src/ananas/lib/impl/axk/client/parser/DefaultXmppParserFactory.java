package ananas.lib.impl.axk.client.parser;

import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import ananas.lib.axk.XmppEnvironment;
import ananas.lib.axk.element.stream.Ctrl_stream;
import ananas.lib.axk.element.stream.XmppStreamListener;
import ananas.lib.axk.element.stream.Xmpp_stream;
import ananas.lib.blueprint.core.dom.BPDocument;
import ananas.lib.blueprint.core.dom.BPElement;
import ananas.lib.blueprint.core.lang.BPEnvironment;
import ananas.lib.blueprint.core.lang.BPNamespace;
import ananas.lib.blueprint.core.lang.BPNamespaceRegistrar;
import ananas.lib.blueprint.core.lang.BPType;
import ananas.lib.blueprint.core.util.BPBuilder;
import ananas.lib.blueprint.core.util.BPElementProvider;
import ananas.lib.blueprint.core.util.BPErrorHandler;
import ananas.lib.blueprint.core.util.BPXMLReaderFactory;

public class DefaultXmppParserFactory implements XmppParserFactory {

	final static Logger logger = LogManager.getLogger(new Object() {
	});

	@Override
	public XmppParser newParser(XmppEnvironment envi) {
		return new MyParser(envi);
	}

	class MyParser implements XmppParser {

		private final XmppEnvironment mEnvi;

		public MyParser(XmppEnvironment envi) {
			this.mEnvi = envi;
		}

		@Override
		public void parse(InputStream is, XmppParserCallback callback)
				throws SAXException, IOException {

			final BPEnvironment envi = this.mEnvi.getBPEnvironment();

			BPDocument doc = envi.getImplementation().createDocument(envi,
					"xmpp-dom");

			BPBuilder builder = envi.getBuilderFactory().newBuilder(doc);
			builder.setBPElementProvider(new MyElementProvider(envi, callback));
			builder.setBPErrorHandler(new MyErrorHandler(envi, callback));

			// BPXMLReaderFactory readerFactory = envi.getXMLReaderFactory();
			BPXMLReaderFactory readerFactory = new TempReaderFactory();
			XMLReader reader = readerFactory.newReader();

			reader.setContentHandler(builder.getContentHandler());
			reader.setErrorHandler(builder.getErrorHandler());

			InputSource is2 = new InputSource(new MyInputStreamProxy(is));
			reader.parse(is2);

		}
	}

	static class MyErrorHandler implements BPErrorHandler {

		public MyErrorHandler(BPEnvironment envi, XmppParserCallback callback) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void error(Exception e) throws Exception {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void fatalError(Exception e) throws Exception {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void warning(Exception e) throws Exception {
			// TODO Auto-generated constructor stub
		}
	}

	class MyCallbackToStreamListenerAdapter implements XmppStreamListener {

		private final XmppParserCallback mCallback;

		public MyCallbackToStreamListenerAdapter(XmppParserCallback callback) {
			this.mCallback = callback;
		}

		@Override
		public void onReceive(Xmpp_stream stream, Object object) {
			this.mCallback.onReceive(stream, object);
		}
	}

	class MyElementProvider implements BPElementProvider {

		private final XmppParserCallback mXmppParserCallback;
		// private final BPEnvironment mEnvi;
		private final BPNamespaceRegistrar mNSReg;

		public MyElementProvider(BPEnvironment envi, XmppParserCallback callback) {
			this.mXmppParserCallback = callback;
			// this.mEnvi = envi;
			this.mNSReg = envi.getNamespaceRegistrar();
		}

		@Override
		public BPElement createElement(BPDocument doc, String uri,
				String localName) {

			BPElement element = this._createElement(doc, uri, localName);
			if (element == null) {
				logger.warn("Warning:no element with name of " + uri + "#"
						+ localName);
			} else if (element instanceof Ctrl_stream) {
				Ctrl_stream ctrlStream = (Ctrl_stream) element;
				Xmpp_stream xmppStream = ctrlStream.getTarget_stream();
				xmppStream.setListener(new MyCallbackToStreamListenerAdapter(
						this.mXmppParserCallback));
			}
			return element;
		}

		private BPElement _createElement(BPDocument doc, String uri,
				String localName) {
			BPNamespace ns = this.mNSReg.getNamespace(uri);
			if (ns == null)
				return null;
			BPType type = ns.getType(localName);
			if (type == null)
				return null;
			return type.createElement(doc);
		}
	}

	class MyInputStreamProxy extends InputStream {

		private final InputStream mIS;

		public MyInputStreamProxy(InputStream is) {
			this.mIS = is;
		}

		@Override
		public int read() throws IOException {
			int b = this.mIS.read();
			return b;
		}

		@Override
		public int available() throws IOException {
			return this.mIS.available();
		}

		@Override
		public void close() throws IOException {
			this.mIS.close();
		}

		@Override
		public synchronized void mark(int readlimit) {
			this.mark(readlimit);
		}

		@Override
		public boolean markSupported() {
			return this.mIS.markSupported();
		}

		@Override
		public synchronized void reset() throws IOException {
			this.mIS.reset();
		}

		@Override
		public long skip(long n) throws IOException {
			return this.mIS.skip(n);
		}

	}

}
