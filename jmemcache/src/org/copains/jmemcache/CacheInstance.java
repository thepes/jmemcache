package org.copains.jmemcache;

import java.util.Calendar;
import java.util.Hashtable;

import org.copains.jmemcache.interfaces.JMemCacheable;
import org.copains.jmemcache.objects.GenericMemCacheable;

public class CacheInstance {
	
	private long cacheLifetime;
	private String instanceName; 
	
	private Hashtable<String, JMemCacheable> cache;
	private Hashtable<String, GenericMemCacheable> genericCache;
	
	public CacheInstance(String name)
	{
		instanceName = name;
	}
	
	public String addToCache(JMemCacheable cacheable)
	{
		if (null == cacheable)
			return (null);
			
		if (null == cache)
			cache = new Hashtable<String, JMemCacheable>();
		Calendar cal = Calendar.getInstance();
		cacheable.setExpirationDate(cal.getTimeInMillis()+cacheLifetime);
		if (null != cache.put(cacheable.getKey(), cacheable))
			return (cacheable.getKey());
		return (null);
	}
	
	public String addToCache(Object o,String key)
	{
		if (null == o)
			return (null);
		if ((null == key) || (key.length() < 1))
			return (null);
		
		if (null == genericCache)
			genericCache = new Hashtable<String, GenericMemCacheable>();
		GenericMemCacheable gmc = new GenericMemCacheable(o);
		Calendar cal = Calendar.getInstance();
		long exp = cal.getTimeInMillis() + cacheLifetime;
		gmc.setExpirationDate(exp);
		if (null != genericCache.put(key, gmc))
			return (key);
		return (null);
	}
	
	/**
	 * returns object in cache or update it if necessary (if obect type is generic and object is expired
	 * then returns null) 
	 * @param key the unique key used to store the object
	 * @return object in cache or null if the object type is generic and object has expired
	 */
	public Object get(String key)
	{
		if ((null == key) || (key.length() < 1))
			return (null);
		
		if (null != cache)
		{
			JMemCacheable obj = cache.get(key);
			if (null == obj)
				return (null);
			if (hasExpired(obj))
				obj.update();
			return (obj);
		}
		if (null != genericCache)
		{
			GenericMemCacheable obj = genericCache.get(key);
			if (null == obj)
				return (null);
			if (hasExpired(obj))
				return (null);
			return (obj);			
		}
		return (null);
	}

	public long getCacheLifetime() {
		return cacheLifetime;
	}

	public void setCacheLifetime(long cacheLifetime) {
		this.cacheLifetime = cacheLifetime;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	
	public boolean hasExpired(JMemCacheable obj)
	{
		Calendar cal = Calendar.getInstance();
		if (obj.getExpirationDate() < cal.getTimeInMillis())
			return (true);
		return (false);
	}

}
