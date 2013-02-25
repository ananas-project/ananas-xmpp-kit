package ananas.lib.impl.axk.client.target;

import ananas.lib.axk.XmppClientExAPI;
import ananas.lib.axk.XmppPhase;
import ananas.lib.axk.api.IExConnection;

public class Tar_connection extends Tar_abstractClient implements IExConnection {

	@Override
	public XmppClientExAPI getExAPI(Class<? extends XmppClientExAPI> apiClass) {
		if (apiClass.equals(IExConnection.class)) {
			IExConnection api = this;
			return api;
		} else {
			return super.getExAPI(apiClass);
		}
	}

	@Override
	public void setStatus(XmppPhase phase) {
		// TODO Auto-generated method stub

	}

	@Override
	public XmppPhase getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppPhase getPhase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}

}
