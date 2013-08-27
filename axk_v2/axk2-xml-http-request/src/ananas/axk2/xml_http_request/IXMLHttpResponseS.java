package ananas.axk2.xml_http_request;

public interface IXMLHttpResponseS extends IXMLHttpResponseA {

	void setHeader(String name, String value);

	void setResponseCode(int code);

	void setContent(String content);

}
