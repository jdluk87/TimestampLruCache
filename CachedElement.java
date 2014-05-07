package org.fao.ciokw.mobile.cache;

/**
 * @CachedElement has two fields: <V> and a timestamp. The timestamp is used to validate the 
 * extracted element if found in cache. This means that if we are looking for an element and such 
 * element has been found in cache but the element timestamp is older than the timestamp of the 
 * element we are looking for, this element is old and has to be deleted from the cache.
 * @author lucalupo
 *
 * @param <V>
 */
public class CachedElement<V> {

	private long timestamp;
	private V value;
	
	/**
	 * Use this constructor if you want to use the standard 
	 * LruCache included in {@link android.util.LruCache}
	 */
	public CachedElement(){}
	
	/**
	 * Use this constructor if you want to use this custom
	 * Caching mechanism.
	 * @param value, timestamp
	 */
	public CachedElement(V value, long timestamp){
		this.setTimestamp(timestamp);
		this.value = value;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
