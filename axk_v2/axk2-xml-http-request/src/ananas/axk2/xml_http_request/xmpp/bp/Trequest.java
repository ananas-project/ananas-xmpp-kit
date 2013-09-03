package ananas.axk2.xml_http_request.xmpp.bp;

public class Trequest {

	private String _version;
	private String _url;
	private String _method;
	private Theader _header;
	private Tcontent _content;

	public void setVersion(String value) {
		this._version = value;
	}

	public void setURL(String value) {
		this._url = value;
	}

	public void setMethod(String value) {
		this._method = value;
	}

	public String setVersion() {
		return this._version;
	}

	public String setURL() {
		return this._url;
	}

	public String setMethod() {
		return this._method;
	}

	public void setHeader(Theader header) {
		this._header = header;
	}

	public void setContent(Tcontent content) {
		this._content = content;
	}

	public Tcontent getContent() {
		return this._content;
	}

	public Theader getHeader() {
		return this._header;
	}

	public String getURL() {
		return this._url;
	}

	public String getMethod() {
		return this._method;
	}

}
