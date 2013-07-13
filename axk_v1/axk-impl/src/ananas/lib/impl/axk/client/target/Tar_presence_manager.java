package ananas.lib.impl.axk.client.target;

import ananas.lib.axk.XmppAddress;
import ananas.lib.axk.XmppClientExAPI;
import ananas.lib.axk.XmppEvent;
import ananas.lib.axk.api.IExConnection;
import ananas.lib.axk.api.presence.IExPresenceManager;
import ananas.lib.axk.api.presence.IPresenceRes;
import ananas.lib.axk.api.presence.IPresenceSet;
import ananas.lib.axk.constant.XmppShow;
import ananas.lib.axk.constant.XmppStatus;
import ananas.lib.axk.element.jabber_client.Xmpp_presence;
import ananas.lib.axk.event.DataEvent;
import ananas.lib.axk.event.PhaseEvent;

public class Tar_presence_manager extends Tar_abstractClient implements
		IExPresenceManager {

	// private final Map<XmppAddress, Xmpp_presence> mPresenceMap;
	private boolean m_isAutoPresenceAfterBinding;
	private XmppShow m_my_show;

	public Tar_presence_manager() {
		// this.mPresenceMap = new Hashtable<XmppAddress, Xmpp_presence>();
		this.m_isAutoPresenceAfterBinding = true;
		this.m_my_show = XmppShow.empty;
	}

	@Override
	public XmppClientExAPI getExAPI(Class<? extends XmppClientExAPI> apiClass) {
		if (IExPresenceManager.class.equals(apiClass)) {
			IExPresenceManager api = this;
			return api;
		} else {
			return super.getExAPI(apiClass);
		}
	}

	@Override
	public boolean isAutoPresenceAfterBinding() {
		return this.m_isAutoPresenceAfterBinding;
	}

	@Override
	public void setAutoPresenceAfterBinding(boolean value) {
		this.m_isAutoPresenceAfterBinding = value;
	}

	private void _doSendMyPresence(String presence) {
		if (presence != null) {
			IExConnection conn = (IExConnection) this
					.getExAPI(IExConnection.class);
			conn.sendStanza(presence);
		}
	}

	@Override
	public void onEvent(XmppEvent event) {
		if (event instanceof DataEvent) {
			DataEvent de = (DataEvent) event;
			Object data = de.getData();
			if (data instanceof Xmpp_presence) {
				Xmpp_presence pres = (Xmpp_presence) data;
				this.onReceiveStanzaPresence(data, pres);
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
			if (this.m_isAutoPresenceAfterBinding) {
				StringBuilder sb = new StringBuilder();
				sb.append("<presence>");
				sb.append("<show>" + this.m_my_show + "</show>");
				sb.append("</presence>");
				this._doSendMyPresence(sb.toString());
			}
			IExConnection conn = (IExConnection) this
					.getExAPI(IExConnection.class);
			XmppAddress bind = conn.getBindingJID();
			System.out.println("bind:" + bind);
		}
	}

	// final Map < XmppAddress, > mTableForPrescen ;

	private void onReceiveStanzaPresence(Object data, Xmpp_presence pres) {

		// XmppAddress addrFrom = pres.getFrom();
		;

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public IPresenceSet getPresenceSet(XmppAddress jid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPresenceRes getPresenceRes(XmppAddress jid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMyPresenceShow(XmppShow show) {
		if (show != null) {
			this.m_my_show = show;
		}
	}

	@Override
	public XmppShow getMyPresenceShow() {
		return this.m_my_show;
	}

}
