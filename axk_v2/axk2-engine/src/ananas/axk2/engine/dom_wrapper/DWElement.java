package ananas.axk2.engine.dom_wrapper;

import java.util.List;

import org.w3c.dom.Element;

public interface DWElement extends DWNode {

	Element getElement();

	List<DWElement> listChildElements();

	String getChildText();
}
