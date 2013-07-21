package impl.ananas.axk2.core.base.bp;

import impl.ananas.axk2.core.base.AXK;
import impl.ananas.axk2.core.base.Connection;

import org.w3c.dom.Document;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppConnector;
import ananas.axk2.core.XmppContext;
import ananas.blueprint4.core.BPEnvironment;
import ananas.blueprint4.core.lang.BPDocument;
import ananas.blueprint4.core.util.BPDocumentBuilder;

public class XmppConnectorImpl implements XmppConnector {

	private final Document _config;

	public XmppConnectorImpl(Document config) {
		this._config = config;
	}

	@Override
	public XmppConnection openConnection(XmppContext context,
			XmppAccount account) {
		BPEnvironment bp = context.getBlueprintEnvironment();
		BPDocumentBuilder bpBuilder = bp.getBPDocumentBuilderFactory()
				.createBuilder();
		BPDocument bpDoc = bpBuilder.build(bp, _config);
		AXK axk = (AXK) bpDoc.getRootElement().getTarget(true);
		Connection conn = axk.getConnection();
		conn.init(context, account);
		return conn.getFacade();
	}

}
