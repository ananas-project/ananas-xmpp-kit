package test.ananas.lib.axk.helper.dom;

import java.util.HashMap;
import java.util.Map;

public class MyTextGroup {

	private final Map<String, String> mMap = new HashMap<String, String>();

	public void addText(MyText text) {
		this.mMap.put(text.getId(), text.getText());
	}

	public String getText(String cmd) {
		return this.mMap.get(cmd);
	}

}
