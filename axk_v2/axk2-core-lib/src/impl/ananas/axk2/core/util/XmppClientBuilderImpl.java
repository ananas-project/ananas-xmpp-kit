package impl.ananas.axk2.core.util;

import java.io.InputStream;
import java.util.Properties;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEnvironment;
import ananas.axk2.core.util.XmppClientBuilder;
import ananas.blueprint4.core.BPEnvironment;
import ananas.blueprint4.core.lang.BPDocument;

public class XmppClientBuilderImpl implements XmppClientBuilder {

	private InputStream _in;

	@Override
	public XmppClientBuilder setJID(String jid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppClientBuilder setPassword(String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppClientBuilder setResource(String resource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppClientBuilder setHost(String host) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppClientBuilder setPort(int port) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppClientBuilder setUseSSL(boolean useSSL) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppClientBuilder loadAccount(Properties prop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppClientBuilder loadAccount(InputStream in) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppClientBuilder loadConfigXML(InputStream in) {
		this._in = in;
		return this;
	}

	@Override
	public XmppConnection createClient() {

		try {
			XmppEnvironment envi = XmppEnvironment.Factory.getDefault();
			BPEnvironment bp = envi.getBlueprintEnvironment();
			InputStream in = this._getConfigXmlInput();
			BPDocument doc;
			// doc = bp.loadBPDocument(this._getConfigXmlInput(), "");
			doc = bp.loadBPDocument(in);

			Object targetRoot = doc.getRootElement().getTarget(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private InputStream _getConfigXmlInput() {
		InputStream in = _in;
		_in = null;
		if (in == null) {
			String name = this.getClass().getSimpleName() + ".xml";
			in = this.getClass().getResourceAsStream(name);
			if (in == null) {
				System.err.println("cannot find file " + name);
			}
		}
		return in;
	}
}
