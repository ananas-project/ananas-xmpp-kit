package ananas.axk2.engine.dom_wrapper;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class AbstractElementWrapper implements DWElement {

	private final DWDocument _ownerDoc;
	private final Element _element;

	protected AbstractElementWrapper(Element element, DWDocument owner) {
		this._element = element;
		this._ownerDoc = owner;
	}

	@Override
	public Node getNode() {
		return this.getNode();
	}

	@Override
	public DWDocument getOwnerDocument() {
		return this._ownerDoc;
	}

	@Override
	public Element getElement() {
		return this._element;
	}

	public String toString() {
		String uri = this._element.getNamespaceURI();
		String localName = this._element.getLocalName();
		return (this.getClass() + "('" + uri + "#" + localName + "')");
	}

	@Override
	public List<DWElement> listChildElements() {
		return this.__getMyChildrenList().getElements();
	}

	@Override
	public String getChildText() {
		return this.__getMyChildrenList().getText();
	}

	private MyChildrenCache _children;

	private MyChildrenCache __getMyChildrenList() {
		MyChildrenCache cache = this._children;
		if (cache == null) {
			cache = new MyChildrenCache(this);
			cache.load(this._element);
			this._children = cache;
		}
		return cache;
	}

	private class MyChildrenCache {

		private String _text;
		private List<DWElement> _elements;
		private final DWElement _self;

		public MyChildrenCache(DWElement self) {
			this._self = self;
		}

		public void load(Element element) {
			DWDocument doc = _self.getOwnerDocument();
			StringBuilder sb = new StringBuilder();
			List<DWElement> list = new ArrayList<DWElement>();
			NodeList chs = element.getChildNodes();
			int len = chs.getLength();
			for (int i = 0; i < len; i++) {
				Node ch = chs.item(i);
				if (ch instanceof Element) {
					Element chEle = (Element) ch;
					DWElement ele2 = doc.wrapElement(chEle);
					list.add(ele2);
				} else if (ch instanceof Text) {
					Text chText = (Text) ch;
					sb.append(chText.getData());
				}
			}
			this._elements = list;
			this._text = sb.toString();
		}

		public List<DWElement> getElements() {
			return this._elements;
		}

		public String getText() {
			return this._text;
		}
	}

	@Override
	public void printTree(PrintStream out, String tab) {

		out.println(tab + "@" + this);

		List<DWElement> chs = this.listChildElements();
		for (DWElement ch : chs) {
			ch.printTree(out, tab + "    ");
		}
	}

}
