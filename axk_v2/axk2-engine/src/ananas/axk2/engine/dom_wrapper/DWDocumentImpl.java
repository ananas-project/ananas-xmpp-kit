package ananas.axk2.engine.dom_wrapper;

import java.io.PrintStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ananas.lib.util.logging.Logger;

public class DWDocumentImpl implements DWDocument {

	final static Logger log = Logger.Agent.getLogger();

	private final Document _dom;
	private final DOMWrapperImplementation _impl;
	private DWElement _root;

	public DWDocumentImpl(Document doc, DOMWrapperImplementation impl) {
		this._dom = doc;
		this._impl = impl;
	}

	@Override
	public Node getNode() {
		return this._dom;
	}

	@Override
	public DWDocument getOwnerDocument() {
		return null;
	}

	@Override
	public DOMWrapperImplementation getImplementation() {
		return this._impl;
	}

	@Override
	public DWElement wrapElement(Element element) {
		DOMWrapperFactory factory = this._impl.getRegistrar().getFactory(
				element);
		if (factory == null) {
			String uri = element.getNamespaceURI();
			String lname = element.getLocalName();
			log.warn("no wrap for element : " + uri + "#" + lname);
			factory = DefaultElementWrapper.getFactory();
		}
		return factory.wrapElement(element, this);
	}

	@Override
	public void printTree(PrintStream out, String tab) {
		DWElement root = this.__getRoot();
		root.printTree(out, "");
	}

	private DWElement __getRoot() {
		DWElement root = this._root;
		if (root == null) {
			root = this.wrapElement(this._dom.getDocumentElement());
			this._root = root;
		}
		return root;
	}

}
