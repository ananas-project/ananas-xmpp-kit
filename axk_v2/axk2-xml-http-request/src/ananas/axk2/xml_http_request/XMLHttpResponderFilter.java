package ananas.axk2.xml_http_request;

import ananas.axk2.core.AbstractFilter;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.xml_http_request.util.ResponderHelper;
import ananas.axk2.xml_http_request.xmpp.bp.Trequest;

public abstract class XMLHttpResponderFilter extends AbstractFilter implements
		IXMLHttpResponder {

	@Override
	public XmppCommand filter(XmppCommand command) {
		if (command instanceof StanzaCommand) {
			StanzaCommand sc = (StanzaCommand) command;
			Object obj = sc.getObject();
			if (obj instanceof Trequest) {
				final Trequest req = (Trequest) obj;
				final XmppConnection conn = this.getConnection();
				final ResponderHelper helper = new ResponderHelper(conn, req);
				this.process(helper.getRequest(), helper.getResponse());
				conn.dispatch(helper.buildEvent());
			}
		}
		return super.filter(command);
	}

}
