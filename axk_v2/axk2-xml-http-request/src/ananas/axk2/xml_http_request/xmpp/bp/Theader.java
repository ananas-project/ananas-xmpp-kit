package ananas.axk2.xml_http_request.xmpp.bp;

import java.util.HashMap;
import java.util.Map;

public class Theader {

	private final Map<String, String> _fields = new HashMap<String, String>();

	public void setField(Tfield field) {
		_fields.put(field.getName(), field.getValue());
	}

}
