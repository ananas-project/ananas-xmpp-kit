package ananas.axk2.xml_http_request;

public interface IXMLHttpResponseA {

	String getHeader(String name);

	int getResponseCode();

	String getResponseMessage();

	String getVersion();

	String getContent();

}
