package org.fao.ciokw.mobile.cache;

import android.util.LruCache;

/**
 * This class makes use of the Android LruCache but it wraps the object <V> inside @CachedElement. 
 * @CachedElement has two fields: <V> and a timestamp. The timestamp is used to validate the 
 * extracted element if found in cache. This means that if we are looking for an element and such 
 * element has been found in cache but the element timestamp is older than the timestamp of the 
 * element we are looking for, this element is old and has to be deleted from the cache.
 * @author lucalupo
 *
 * @param <K>
 * @param <V>
 */
public class TimestampLruCache<K, V>{
	private LruCache<K, CachedElement<V>> mMemoryCache;
	
	public TimestampLruCache(int cacheSize) {
	    mMemoryCache = new LruCache<K, CachedElement<V>>(cacheSize);
	}
	
	public final V get(K key, long timestamp) {
		CachedElement<V> mapValue = null;
		
		try{
			mapValue = mMemoryCache.get(key);
		}catch(NullPointerException npe){
			 throw new NullPointerException("key == null");
		}
		
		if (mapValue == null) return null;
		
		/*
		 * The element is cached, however we have to check if it is not an old element.
		 * If the timestamp is newer than the chached element timestamp means that we 
		 * are dealing with an old element, thus we remove the element from the Lru
		 * cache and return null.
		 */
		
		if (mapValue.getTimestamp() < timestamp){
			mMemoryCache.remove(key);
			return null;
		}
		
		return (V) mapValue.getValue();
	}
	
	/**
     * Caches {@code value} for {@code key} with {@code timestamp}. The value is moved 
     * to the head of the queue.
     *
     * @return the previous value mapped by {@code key}.
     */
	public final V put(K key, V value, long timestamp) {
		if (key == null || value == null || timestamp < 0) {
            throw new NullPointerException("key == null || value == null || timestamp < 0");
        }
		
		CachedElement<V> cachedElement = new CachedElement<V>(value, timestamp);
		CachedElement<V> previous = mMemoryCache.put(key, cachedElement);
		
		if (previous == null) return null;
		return (V) previous.getValue();
	}
}
