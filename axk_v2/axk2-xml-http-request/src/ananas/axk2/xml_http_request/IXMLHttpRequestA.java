package ananas.axk2.xml_http_request;

public interface IXMLHttpRequestA extends IXMLHttpRequestS {

	void open(String method, String url);

	void setListener(IXMLHttpRequestListener listener);

	void send(String content);

	void setHeader(String name, String value);

}
