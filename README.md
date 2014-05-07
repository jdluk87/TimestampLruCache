TimestampLRUCache
====================

TimestampLRUCache is a Last-Recently-Used-policy cache that employs the android.util.LruCache to store any kind of Java object. The difference between TimestampLRUCache and android.util.LruCache is that in TimestampLRUCache it is used an additional timestamp to validate an object present in cache.

The cache is useful whenever the information might change within short time and so an object stored in cache at time t might be different from the same object extracted from the cache at time t+x, if during time x the object has changed.

Scenario
====================

Let's say that what we extract is an url of an image news from a web service, called imageUrl. The service also returns a timestamp of the last modified date of the news. Let's call it lastUpdateTimestamp.

When we retrieve imageUrl and lastUpdateTimestamp first time the cache will not contain such an element (we can suppose to use the url as the key of the cache element), so we download the image and store it inside the cache. If then we need to retrieve the same image, this might be present in the cache. If so, we do not simply check the presence of the image inside the cache, but also it's timestamp.

How to use it
====================

I included also an Utility class to initialize and use the cache with bitmaps objects. The class is called CacheUtil.java

License
====================

TimestampLRUCache is distributed under the MIT License.


Thank you!
====================

Hope you will find it useful.
