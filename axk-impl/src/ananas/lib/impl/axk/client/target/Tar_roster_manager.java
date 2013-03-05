package ananas.lib.impl.axk.client.target;

import java.util.List;

import ananas.lib.axk.XmppClientExAPI;
import ananas.lib.axk.XmppEvent;
import ananas.lib.axk.api.IExConnection;
import ananas.lib.axk.api.IExRosterManager;
import ananas.lib.axk.element.iq_roster.Xmpp_query;
import ananas.lib.axk.element.jabber_client.Xmpp_iq;
import ananas.lib.axk.event.DataEvent;
import ananas.lib.axk.util.XmppStanzaBuilder;

public class Tar_roster_manager extends Tar_abstractClient implements
		IExRosterManager {

	private Xmpp_query mRosterQuery;
	private boolean mIsAutoPullAfterBinding;
	private int mIdCount = 1;

	public Tar_roster_manager() {
	}

	@Override
	public XmppClientExAPI getExAPI(Class<? extends XmppClientExAPI> apiClass) {
		if (IExRosterManager.class.equals(apiClass)) {
			IExRosterManager api = this;
			return api;
		} else {
			return super.getExAPI(apiClass);
		}
	}

	@Override
	public void pullRoster(boolean isLazy) {
		if (isLazy) {
			if (this.mRosterQuery == null) {
				this._doPull();
			}
		} else {
			this._doPull();
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
	public Xmpp_query getRoster() {
		return this.mRosterQuery;
	}

	@Override
	public boolean isAutoPullAfterBinding() {
		return this.mIsAutoPullAfterBinding;
	}

	@Override
	public void setAutoPullAfterBinding(boolean value) {
		this.mIsAutoPullAfterBinding = value;
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
		}
		super.onEvent(event);
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
