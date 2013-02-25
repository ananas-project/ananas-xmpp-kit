package ananas.lib.impl.axk.client.parser;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import ananas.lib.axk.XmppEnvironment;
import ananas.lib.blueprint.core.dom.BPDocument;
import ananas.lib.blueprint.core.lang.BPEnvironment;
import ananas.lib.blueprint.core.util.BPBuilder;

public class DefaultXmppParserFactory implements XmppParserFactory {

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

			XMLReader reader = envi.getXMLReaderFactory().newReader();
			reader.setContentHandler(builder.getContentHandler());
			reader.setErrorHandler(builder.getErrorHandler());

			InputSource is2 = new InputSource(new MyInputStreamProxy(is));
			reader.parse(is2);

		}
	}

	class MyInputStreamProxy extends InputStream {

		private final InputStream mIS;

		public MyInputStreamProxy(InputStream is) {
			this.mIS = is;
		}

		@Override
		public int read() throws IOException {

			long m1 = System.currentTimeMillis();
			int b = this.mIS.read();
			long m2 = System.currentTimeMillis();

			// System.out.write(b);
			// System.out.flush();

			if ((m2 - m1) > 1000) {
				System.out.println("blocked!");
			}

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
