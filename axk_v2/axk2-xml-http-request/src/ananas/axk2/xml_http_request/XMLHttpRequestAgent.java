package ananas.axk2.xml_http_request;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import ananas.axk2.core.AbstractFilter;
import ananas.axk2.core.DefaultAddress;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.api.ICommandAgent;
import ananas.axk2.core.command.StanzaCommand;

public class XMLHttpRequestAgent extends AbstractFilter implements
		IXMLHttpRequestAgent {

	private int _id_count;

	public XMLHttpRequestAgent() {
		this._id_count = 1;
	}

	@Override
	public IXMLHttpRequest newRequest() {
		String id = "xhreq-" + this.hashCode() + "-" + (this._id_count++);
		return new MyRequest(id);
	}

	@Override
	public XmppEvent filter(XmppEvent event) {
		return super.filter(event);
	}

	class MyRequest implements IXMLHttpRequest {

		private final long _create_time;
		private final String _id;
		private final Map<String, String> _headers = new HashMap<String, String>();
		private IXMLHttpRequestListener _listener;
		private String _method;
		private String _url;
		private final String _version = "HTTP/1.0";

		public MyRequest(String id) {
			this._id = id;
			this._create_time = System.currentTimeMillis();
		}

		@Override
		public void open(String method, String url) {
			this._method = method;
			this._url = url;
		}

		@Override
		public void setListener(IXMLHttpRequestListener listener) {
			this._listener = listener;
		}

		@Override
		public void send(String content) {
			XmppConnection conn = XMLHttpRequestAgent.this.getConnection();
			ICommandAgent cmd_agent = (ICommandAgent) conn
					.getAPI(ICommandAgent.class);
			StanzaCommand cmd = cmd_agent.newStanzaCommand(conn);

			cmd.setString("<iq xmlns='jabber:client' ></iq>");
			Element iq = cmd.getElement();

			XmppAddress addr = new DefaultAddress(this._url);

			iq.setAttribute("type", "get");
			iq.setAttribute("id", this._id);
			iq.setAttribute("to", addr.toString());

			final String xmlns = "ananas:xmpp:xml_http_request";
			Document doc = iq.getOwnerDocument();
			Element request = doc.createElementNS(xmlns, "request");
			request.setAttribute("method", this._method);
			request.setAttribute("version", this._version);
			{
				URI uri = URI.create(this._url);
				String path, query, host;
				host = uri.getHost();
				path = uri.getPath();
				query = uri.getQuery();
				request.setAttribute("url", path + "" + query);
				this._headers.put("Host", host + "");
			}

			Element header = doc.createElement("header");
			Set<String> keys = this._headers.keySet();
			for (String key : keys) {
				String value = this._headers.get(key);
				Element field = doc.createElement("field");
				field.setAttribute("name", key);
				field.setAttribute("value", value);
				header.appendChild(field);
			}
			request.appendChild(header);

			if (content != null) {
				Element eleContent = doc.createElement("content");
				Text txt = doc.createTextNode(content);
				eleContent.appendChild(txt);
				request.appendChild(eleContent);
			}

			iq.appendChild(request);

			cmd.setElement(iq);
			conn.send(cmd);
		}

		@Override
		public void setHeader(String name, String value) {
			this._headers.put(name, value);
		}
	}

}
