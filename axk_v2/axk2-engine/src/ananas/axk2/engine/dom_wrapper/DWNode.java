package ananas.axk2.engine.dom_wrapper;

import java.io.PrintStream;

import org.w3c.dom.Node;

public interface DWNode {

	Node getNode();

	DWDocument getOwnerDocument();

	void printTree(PrintStream out, String tab);

}
