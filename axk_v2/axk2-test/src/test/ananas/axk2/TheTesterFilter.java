package test.ananas.axk2;

import ananas.axk2.core.DefaultAddress;
import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.XmppStatus;
import ananas.axk2.core.event.PhaseEvent;
import ananas.axk2.core.event.StanzaEvent;
import ananas.axk2.xep.xep_0033.IServiceDiscoveryManager;

public class TheTesterFilter implements XmppFilter {

	private XmppConnection _conn;

	@Override
	public int findAPI(Class<?> apiClass, XmppAPIHandler h) {
		return XmppFilter.find_continue;
	}

	@Override
	public void bind(XmppConnection connection) {
		this._conn = connection;
	}

	@Override
	public void unbind(XmppConnection connection) {
		this._conn = null;
	}

	@Override
	public XmppConnection getConnection() {
		return _conn;
	}

	@Override
	public XmppCommand filter(XmppCommand command) {
		return command;
	}

	@Override
	public XmppEvent filter(XmppEvent event) {

		if (event instanceof PhaseEvent) {
			PhaseEvent pe = (PhaseEvent) event;
			if (pe.getNewPhase().equals(XmppStatus.online)) {
				this.__on_online(pe);
			}
		} else if (event instanceof StanzaEvent) {
			String s = ((StanzaEvent) event).getString();
			System.out.println(this + ".onEvent:" + s);
		}

		return event;
	}

	private void __on_online(PhaseEvent pe) {

		XmppConnection conn = pe.getConnection();
		IServiceDiscoveryManager sdm = (IServiceDiscoveryManager) conn
				.getAPI(IServiceDiscoveryManager.class);
		String domain = conn.getAccount().jid().domain();
		XmppAddress addr = new DefaultAddress(domain);
		sdm.query(addr);

	}
}
