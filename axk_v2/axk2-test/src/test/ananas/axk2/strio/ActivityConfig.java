package test.ananas.axk2.strio;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppContext;
import ananas.axk2.core.util.XmppClientBuilder;

public class ActivityConfig implements XConnConfig {

	@Override
	public XmppConnection openConnection() {

		Object ref = this;
		String fileName = ref.getClass().getSimpleName();

		XmppClientBuilder xcb = XmppClientBuilder.Factory.getDefault();
		xcb.loadAccount(ref, fileName + ".account.properties");
		xcb.loadConfigXML(ref, fileName + ".conn.xml");

		XmppContext context = xcb.getContext();
		XmppAccount account = xcb.getAccount();
		return xcb.getConnector().openConnection(context, account);
	}

}
