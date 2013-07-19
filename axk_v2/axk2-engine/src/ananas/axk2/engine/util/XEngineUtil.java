package ananas.axk2.engine.util;

import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

public class XEngineUtil {

	public static String nodeToString(Node node) {
		DOMImplementationLS ls = (DOMImplementationLS) node.getOwnerDocument()
				.getImplementation().getFeature("LS", "3.0");
		LSSerializer seri = ls.createLSSerializer();
		return seri.writeToString(node);
	}

}
