package ananas.axk2.engine.dom_wrapper;

import java.util.Hashtable;
import java.util.Map;

import org.w3c.dom.Element;

public class DOMWrapperRegistrarImpl implements DOMWrapperRegistrar {

	final Map<String, DOMWrapperFactory> _table = new Hashtable<String, DOMWrapperFactory>();

	@Override
	public DOMWrapperFactory getFactory(String uri, String localName) {
		String key = this._keyFor(uri, localName);
		return _table.get(key);
	}

	private String _keyFor(String uri, String localName) {
		return (uri + "##" + localName);
	}

	@Override
	public DOMWrapperFactory getFactory(Element element) {
		return this.getFactory(element.getNamespaceURI(),
				element.getLocalName());
	}

	@Override
	public void registerFactory(String uri, String localName,
			DOMWrapperFactory factory) {
		String key = this._keyFor(uri, localName);
		this._table.put(key, factory);
	}

}
