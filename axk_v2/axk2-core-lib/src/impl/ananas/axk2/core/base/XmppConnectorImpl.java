package impl.ananas.axk2.core.base;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppConnector;
import ananas.axk2.core.XmppContext;

public class XmppConnectorImpl implements XmppConnector {

	@Override
	public XmppConnection openConnection(XmppContext context,
			XmppAccount account) {

		throw new RuntimeException("no impl");
		// return new XmppConnectionImpl( ) ;
	}

}
