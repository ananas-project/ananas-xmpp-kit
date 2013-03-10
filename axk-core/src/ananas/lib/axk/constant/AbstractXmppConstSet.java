package ananas.lib.axk.constant;

import java.util.HashMap;
import java.util.Map;

public class AbstractXmppConstSet<T> {

	private final Map<String, T> mTable;
	private final T mDefault;

	public AbstractXmppConstSet(T[] array, T defaultValue) {
		Map<String, T> map = new HashMap<String, T>();
		for (T item : array) {
			map.put(item.toString(), item);
		}
		this.mTable = map;
		this.mDefault = defaultValue;
	}

	public T get(String string) {
		T rlt = this.mTable.get(string);
		if (rlt == null) {
			rlt = this.mDefault;
		}
		return rlt;
	}

}
