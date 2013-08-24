package ananas.axk2.stringio.axk_filter;

import java.util.LinkedList;
import java.util.List;

import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.api.ICommandAgent;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.core.event.StanzaEvent;
import ananas.axk2.stringio.IStringIO;
import ananas.axk2.stringio.IStringIOService;

public class StringIOServiceFilter implements XmppFilter, IStringIOService {

	private XmppConnection _conn;
	private final IStringIO _str_io = new MyStrIO();

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
		return this._conn;
	}

	@Override
	public XmppCommand filter(XmppCommand command) {
		return command;
	}

	@Override
	public XmppEvent filter(XmppEvent event) {

		if (event instanceof StanzaEvent) {
			String str = ((StanzaEvent) event).getString();
			if (str != null)
				this.__event_str_io(str, true);
		}

		return event;
	}

	class MyStrIO implements IStringIO {

		@Override
		public String io(String cmd) {
			StringIOServiceFilter pthis = StringIOServiceFilter.this;
			XmppConnection conn = pthis.getConnection();
			// command
			if (cmd != null) {
				ICommandAgent ca = (ICommandAgent) conn
						.getAPI(ICommandAgent.class);
				StanzaCommand xcmd = ca.newStanzaCommand(conn);
				xcmd.setString(cmd);
				conn.send(xcmd);
			}
			// event
			String str = pthis.__event_str_io(null, false);
			return str;
		}
	}

	@Override
	public IStringIO getIO() {
		return this._str_io;
	}

	private final List<String> _event_list = new LinkedList<String>();

	private synchronized String __event_str_io(String in, boolean doInput) {
		if (doInput) {
			if (in != null) {
				this._event_list.add(in);
			}
		} else {
			if (this._event_list.size() > 0) {
				return this._event_list.remove(0);
			}
		}
		return null;
	}

	@Override
	public int listAPI(XmppAPIHandler h) {
		return XmppAPIHandler.Util.apiOfObject(this, h);
	}

}
