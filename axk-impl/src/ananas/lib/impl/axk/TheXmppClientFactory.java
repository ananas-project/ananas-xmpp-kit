package ananas.lib.impl.axk;

import java.io.InputStream;

import ananas.lib.axk.XmppAccount;
import ananas.lib.axk.XmppClient;
import ananas.lib.axk.XmppClientFactory;
import ananas.lib.axk.XmppEnvironment;
import ananas.lib.blueprint.core.dom.BPDocument;
import ananas.lib.blueprint.core.lang.BPDocumentLoader;
import ananas.lib.blueprint.core.lang.BPEnvironment;
import ananas.lib.impl.axk.client.target.Tar_connection;

public class TheXmppClientFactory implements XmppClientFactory {

	private XmppEnvironment mEnvi;

	public TheXmppClientFactory() {
		this.mEnvi = this._getDefaultEnvi();
	}

	private XmppEnvironment _getDefaultEnvi() {
		return TheXmppEnvironment.getInstance();
	}

	@Override
	public XmppClient newClient(XmppAccount account) {

		String path = "xmpp-client.xml";
		InputStream in = this.getClass().getResourceAsStream(path);
		if (in == null) {
			throw new RuntimeException("cannot find file " + path);
		}

		TheXmppClientCore core = new TheXmppClientCore(this.mEnvi, account);
		XmppClient client = null;

		try {

			BPEnvironment bp = this.mEnvi.getBPEnvironment();
			BPDocumentLoader docldr = bp.getDocumentLoaderFactory().newLoader();
			BPDocument doc = docldr.loadDocument(bp, in, path);

			Tar_connection conn = (Tar_connection) doc.findTargetById("conn");
			conn.addTarget(core);

			client = (XmppClient) doc.getRootElement().getTarget(true);

		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw ((RuntimeException) e);
			} else {
				throw new RuntimeException(e);
			}
		}

		return client;

	}

	@Override
	public XmppEnvironment getEnvironment() {
		return this.mEnvi;
	}

	@Override
	public void setEnvironment(XmppEnvironment envi) {
		if (envi != null) {
			this.mEnvi = envi;
		}
	}

}
