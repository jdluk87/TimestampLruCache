package org.fao.ciokw.mobile.utils;

import org.fao.ciokw.mobile.cache.TimestampLruCache;
import org.fao.ciokw.mobile.constant.Constants;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * The class implements a LRU cache util class for bitmaps.
 * 
 * @author lucalupo
 *
 */
public class CacheUtil {
	private static CacheUtil instance;
	
	private static TimestampLruCache<String, Bitmap> mMemoryCacheTimestamp;

	public CacheUtil(){
	    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

	    // Use 1/8th of the available memory for this memory cache.
	    final int cacheSize = maxMemory / 8;
	    
	    mMemoryCacheTimestamp = new TimestampLruCache<String, Bitmap>(cacheSize);
	}
	
	/**
	 * It returns always the same instance of the class CacheUtil - singleton
	 * @return
	 */
	public static synchronized CacheUtil getInstance(){
		if(instance == null){
			instance = new CacheUtil();
		}
		return instance;
	}
	
	public void addBitmapToMemoryCache(String key, Bitmap bitmap, long timestamp) {
		if (bitmap != null){
		    if (getBitmapFromMemCache(key, timestamp) == null) {
		    	mMemoryCacheTimestamp.put(key, bitmap, timestamp);
		    }
		}
	}
	
	public static Bitmap getBitmapFromMemCache(String key, long timestamp) {
		return mMemoryCacheTimestamp.get(key, timestamp);
	}

	public Bitmap loadBitmap(String resId, long timestamp) {
	    final Bitmap bitmap = getBitmapFromMemCache(resId, timestamp);
	    
	    if (bitmap != null) {
	    	Log.i(Constants.TAG_CACHE, "cache hit " + resId);
	    	return bitmap;
	    }
	    else {
	    	Log.i(Constants.TAG_CACHE, "cache missing " + resId);
	    	return null;
	    }
	}
}
