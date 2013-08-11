package ananas.axk2.core.bp.target;

import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;

public class DefaultNSFilter implements XmppFilter {

	@Override
	public int findAPI(Class<?> apiClass, XmppAPIHandler h) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void bind(XmppConnection connection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unbind(XmppConnection connection) {
		// TODO Auto-generated method stub

	}

	@Override
	public XmppConnection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppCommand filter(XmppCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppEvent filter(XmppEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

}
