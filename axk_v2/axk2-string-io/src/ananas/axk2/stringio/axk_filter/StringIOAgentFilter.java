package ananas.axk2.stringio.axk_filter;

import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.api.IEventAgent;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.core.event.StanzaEvent;
import ananas.axk2.stringio.IStringIO;
import ananas.axk2.stringio.IStringIOAgent;

public class StringIOAgentFilter implements XmppFilter, IStringIOAgent {

	private IStringIO _str_io;
	private final IStringIO _str_io_facade = new MyIOFacade();
	private XmppConnection _conn;

	@Override
	public void bindIO(IStringIO io) {
		this._str_io = io;
	}

	@Override
	public void bind(XmppConnection connection) {
		this._conn = connection;
	}

	@Override
	public void unbind(XmppConnection connection) {
		this._conn = connection;
	}

	@Override
	public XmppConnection getConnection() {
		return this._conn;
	}

	@Override
	public XmppCommand filter(XmppCommand command) {
		if (command instanceof StanzaCommand) {
			String str = ((StanzaCommand) command).getString();
			if (str != null) {
				this.getIO().io(str);
			}
		}
		return command;
	}

	@Override
	public XmppEvent filter(XmppEvent event) {
		return event;
	}

	class MyIOFacade implements IStringIO {

		@Override
		public String io(String cmd) {

			IStringIO inner = StringIOAgentFilter.this._str_io;
			if (inner == null) {
				return null;
			} else {
				String event = inner.io(cmd);
				if (event != null) {
					StringIOAgentFilter pthis = StringIOAgentFilter.this;
					XmppConnection conn = pthis.getConnection();
					IEventAgent ea = (IEventAgent) conn
							.getAPI(IEventAgent.class);
					StanzaEvent se = ea.newStanzaEvent(conn);
					se.setString(event);
					conn.dispatch(se);
				}
				return event;
			}
		}
	}

	@Override
	public IStringIO getIO() {
		return this._str_io_facade;
	}

	@Override
	public int listAPI(XmppAPIHandler h) {
		return XmppAPIHandler.Util.apiOfObject(this, h);
	}

}
