package ananas.lib.impl.axk.client.target;

import java.util.List;

import ananas.lib.axk.XmppClientExAPI;
import ananas.lib.axk.XmppEvent;
import ananas.lib.axk.api.IExConnection;
import ananas.lib.axk.api.roster.IExRosterManager;
import ananas.lib.axk.constant.XmppStatus;
import ananas.lib.axk.element.iq_roster.Xmpp_query;
import ananas.lib.axk.element.jabber_client.Xmpp_iq;
import ananas.lib.axk.event.DataEvent;
import ananas.lib.axk.event.PhaseEvent;
import ananas.lib.axk.util.XmppStanzaBuilder;
import ananas.lib.impl.axk.client.target.roster.RosterManagerImpl;
import ananas.lib.impl.axk.client.target.roster.IRosterManagerInner;
import ananas.lib.impl.axk.client.target.roster.IRosterManagerInnerCallback;

public class Tar_roster_manager extends Tar_abstractClient {

	private Xmpp_query mRosterQuery;
	private boolean mIsAutoPullAfterBinding;
	private int mIdCount = 1;

	private final IRosterManagerInner mRosterManagerInner = new RosterManagerImpl(
			new MyCallbackFromInner());

	class MyCallbackFromInner implements IRosterManagerInnerCallback {
	}

	public Tar_roster_manager() {
		mIsAutoPullAfterBinding = false;
	}

	@Override
	public XmppClientExAPI getExAPI(Class<? extends XmppClientExAPI> apiClass) {
		if (IExRosterManager.class.equals(apiClass)) {
			IExRosterManager api = this.mRosterManagerInner
					.toIExRosterManager();
			return api;
		} else {
			return super.getExAPI(apiClass);
		}
	}

	private void _doPull() {

		XmppStanzaBuilder xsb = XmppStanzaBuilder.Factory.newInstance();
		xsb.append("<iq type='get' id='roster_" + (this.mIdCount++) + "' >");
		xsb.append("<query xmlns='jabber:iq:roster'/>");
		xsb.append("</iq>");
		String str = xsb.toString();

		IExConnection conn = (IExConnection) this.getExAPI(IExConnection.class);
		conn.sendStanza(str);
	}

	@Override
	public void onEvent(XmppEvent event) {
		if (event instanceof DataEvent) {
			DataEvent de = (DataEvent) event;
			Object data = de.getData();
			if (data instanceof Xmpp_iq) {
				Xmpp_iq iq = (Xmpp_iq) data;
				this.onReceiveStanzaIq(data, iq);
			}
		} else if (event instanceof PhaseEvent) {
			PhaseEvent se = (PhaseEvent) event;
			this.onPhaseEvent(se);
		}
		super.onEvent(event);
	}

	private void onPhaseEvent(PhaseEvent se) {
		XmppStatus pold = se.getOldPhase();
		XmppStatus pnew = se.getNewPhase();
		if (pold.equals(XmppStatus.bind) && pnew.equals(XmppStatus.online)) {
			if (this.mIsAutoPullAfterBinding) {
				if (this.mRosterQuery == null) {
					this._doPull();
				}
			}
		}
	}

	private void onReceiveStanzaIq(Object data, Xmpp_iq iq) {
		List<Object> list = iq.listItems();
		for (Object item : list) {
			if (item instanceof Xmpp_query) {
				this.onReceiveRosterQuery((Xmpp_query) item);
			}
		}
	}

	private void onReceiveRosterQuery(Xmpp_query query) {
		this.mRosterQuery = query;
	}

}
