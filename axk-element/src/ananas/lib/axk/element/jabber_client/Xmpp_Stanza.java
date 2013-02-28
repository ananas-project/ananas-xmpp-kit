package ananas.lib.axk.element.jabber_client;

import java.util.ArrayList;
import java.util.List;

public class Xmpp_Stanza {

	private final List<Object> mItems = new ArrayList<Object>();

	public void addItem(Object item) {
		this.mItems.add(item);
	}

	public List<Object> listItems() {
		return this.mItems;
	}

	public Object findItemByClass(Class<?> cls) {
		for (Object item : this.mItems) {
			if (cls.isInstance(item)) {
				return item;
			}
		}
		return null;
	}

}
