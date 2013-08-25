package ananas.axk2.xml_http_request.component.bp;

import java.util.HashMap;
import java.util.Map;

import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.command.StanzaCommand;

public class TXMLHttpRequestService extends TFilter {

	private final Map<String, XmppFilter> _map = new HashMap<String, XmppFilter>();

	public void add(Tmapping mapping) {
		String url = mapping.getUrlPattern();
		XmppFilter filter = mapping.getFilter();
		this._map.put(url, filter);
	}

	@Override
	public XmppCommand filter(XmppCommand command) {

		if (command instanceof StanzaCommand) {
			Object stanza = ((StanzaCommand) command).getObject();
			if (stanza instanceof String) {
				stanza.toString();
			}
		}

		return super.filter(command);
	}

}
