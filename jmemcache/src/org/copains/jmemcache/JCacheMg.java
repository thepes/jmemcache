package org.copains.jmemcache;

import java.util.Hashtable;
import java.util.Vector;

import org.copains.jmemcache.interfaces.JMemCacheable;

public class JCacheMg {
	
	private static JCacheMg instance;
	
	private Hashtable<String, CacheInstance> cacheList;
	
	private JCacheMg()
	{
		cacheList = new Hashtable<String, CacheInstance>();
	}
	
	public Object getCachedObject(String instanceName,String objKey)
	{
		CacheInstance ci = cacheList.get(instanceName);
		if (null == ci)
			return (null);
		synchronized (ci) {
			return (ci.get(objKey));
		}
	}

	public boolean cache(String instanceName, JMemCacheable o)
	{
		// TODO: add some exceptions. (i.e in case of not initialized cache instance)
		CacheInstance ci = cacheList.get(instanceName);
		if (null == ci)
			return (false);
		synchronized (ci) {
			ci.addToCache((JMemCacheable)o);
		}
		return (true);
	}

	public boolean cache(String instanceName, Object o,String key)
	{
		// TODO: add some exceptions. (i.e in case of not initialized cache instance)
		CacheInstance ci = cacheList.get(instanceName);
		if (null == ci)
			return (false);
		synchronized (ci) {
			ci.addToCache(o,key);
		}
		return (true);
	}
	
	public boolean delete(String instanceName,String objKey)
	{
		CacheInstance ci = cacheList.get(instanceName);
		if (null == ci)
			return (false);
		synchronized (ci) {
			return (ci.delete(objKey));
		}
	}
	
	public int countElements(String instanceName)
	{
		CacheInstance ci = cacheList.get(instanceName);
		if (null == ci)
			return (-1);
		return (ci.countElements());
	}
	
	public Vector<Object> getElements(String instanceName)
	{
		CacheInstance ci = cacheList.get(instanceName);
		if (null == ci)
			return (null);
		return (ci.getElements());
	}

	public void initCacheInstance(String name, long lifetime)
	{
		CacheInstance ci = new CacheInstance(name);
		ci.setCacheLifetime(lifetime);
		synchronized (cacheList) {
			cacheList.put(name, ci);
		}
	}
	
	/**
	 * this method returns the cache instance (package use only)
	 * @param name the instance name
	 * @return
	 */
	protected CacheInstance getCacheInstance(String name)
	{
		return (cacheList.get(name));
	}
	
	/**
	 * get the JCacheMg singleton
	 * @return the JCacheMg
	 */
	public static JCacheMg getInstance()
	{
		if (null == instance)
			initInstance();
		return (instance);
	}
	
	public static void initInstance()
	{
		instance = new JCacheMg();
		try {
			JMemCacheConfig.initConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
