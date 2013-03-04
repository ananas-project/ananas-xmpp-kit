package ananas.lib.axk.event;

import ananas.lib.axk.XmppClient;
import ananas.lib.axk.XmppEventListener;
import ananas.lib.axk.XmppStatus;

public class DefaultPhaseEvent implements PhaseEvent {

	public DefaultPhaseEvent(XmppClient client, XmppStatus oldPhase,
			XmppStatus newPhase) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public XmppClient getClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onReceiveByListener(XmppEventListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public XmppStatus getOldPhase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppStatus getNewPhase() {
		// TODO Auto-generated method stub
		return null;
	}

}
