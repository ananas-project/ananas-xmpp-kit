package ananas.lib.impl.axk.client.target.roster;

import java.util.List;

public interface ICachedMap<K, V> {

	boolean remove(K key);

	boolean removeByValue(V value);

	void clearCache();

	void put(K key, V value);

	V get(K key);

	List<V> listValues();

	int size();

}
