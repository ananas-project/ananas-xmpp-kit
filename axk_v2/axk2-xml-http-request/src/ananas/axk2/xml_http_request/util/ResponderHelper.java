package ananas.axk2.xml_http_request.util;

import java.util.HashMap;
import java.util.Map;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.xml_http_request.IXMLHttpRequestS;
import ananas.axk2.xml_http_request.IXMLHttpResponseS;
import ananas.axk2.xml_http_request.xmpp.bp.Tcontent;
import ananas.axk2.xml_http_request.xmpp.bp.Trequest;

public class ResponderHelper {

	private IXMLHttpResponseS _response;
	private IXMLHttpRequestS _request;

	public ResponderHelper(XmppConnection xmppConnection, Trequest req) {
		this._request = new MyRequest(req);
		this._response = new MyResponse();
	}

	public IXMLHttpRequestS getRequest() {
		return this._request;
	}

	public IXMLHttpResponseS getResponse() {
		return this._response;
	}

	public XmppEvent buildEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	class MyRequest implements IXMLHttpRequestS {

		private Trequest _req;

		public MyRequest(Trequest req) {
			this._req = req;
		}

		@Override
		public String getMethod() {
			return this._req.getMethod();
		}

		@Override
		public String getURL() {
			return this._req.getURL();
		}

		@Override
		public String getContent() {
			Tcontent content = this._req.getContent();
			if (content == null)
				return null;
			return content.getData();
		}

		@Override
		public String getHeader(String name) {
			return this._req.getHeader().getField(name);
		}
	}

	class MyResponse implements IXMLHttpResponseS {

		final Map<String, String> _fields = new HashMap<String, String>();
		private int _resp_code = 0;
		private String _resp_message = "";
		private String _version = "HTTP/1.0";
		private String _content;

		@Override
		public String getHeader(String name) {
			return this._fields.get(name);
		}

		@Override
		public int getResponseCode() {
			return this._resp_code;
		}

		@Override
		public String getResponseMessage() {
			return this._resp_message;
		}

		@Override
		public String getVersion() {
			return this._version;
		}

		@Override
		public String getContent() {
			return this._content;
		}

		@Override
		public void setHeader(String name, String value) {
			this._fields.put(name, value);
		}

		@Override
		public void setResponseCode(int code) {
			this._resp_code = code;
			this._resp_message = getHttpCodeMessageMapper().map(code);
		}

		@Override
		public void setContent(String content) {
			this._content = content;
		}
	}

	private static HttpCodeMessageMapper _hcmm;

	private HttpCodeMessageMapper getHttpCodeMessageMapper() {
		HttpCodeMessageMapper ret = _hcmm;
		if (ret == null) {
		}
		return ret;
	}

	private class HttpCodeMessageMapper {

		public HttpCodeMessageMapper() {
		}

		public String map(int code) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
