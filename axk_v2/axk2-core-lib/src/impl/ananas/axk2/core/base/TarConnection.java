package impl.ananas.axk2.core.base;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEnvironment;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilterManager;

public class TarConnection implements XmppConnection {

	@Override
	public boolean send(XmppCommand cmd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dispatch(XmppEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public XmppAPI getAPI(Class<?> apiClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppAccount getAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppEnvironment getEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppFilterManager getFilterManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
