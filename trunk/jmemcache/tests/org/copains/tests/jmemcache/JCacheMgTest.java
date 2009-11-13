package org.copains.tests.jmemcache;

import junit.framework.TestCase;

import org.copains.jmemcache.JCacheMg;
import org.junit.Test;

public class JCacheMgTest extends TestCase {

	
	
	@Test
	public void testGetCachedObject() {
		String result = "should be this";
		JCacheMg instance = JCacheMg.getInstance();
		instance.cache("testInstance", result,"key");
		for (int i = 0 ; i < 5 ; i++)
		{
			Object o = new Object();
			instance.cache("testInstance", o, ""+i);
		}
		String test = (String)instance.getCachedObject("testInstance", "key");
		assertEquals("Stored object and object in cache not same", result, test);
	}

	@Test
	public void testGetCachedObjectNoInstanceInited() {
		JCacheMg.initInstance();
		JCacheMg cache = JCacheMg.getInstance();
		assertNull(cache.getCachedObject("test", "test"));
	}

	@Test
	public void testDelete() {
		String result = "should be this";
		JCacheMg instance = JCacheMg.getInstance();
		instance.cache("testInstance", result,"key");
		for (int i = 0 ; i < 5 ; i++)
		{
			Object o = new Object();
			instance.cache("testInstance", o, ""+i);
		}
		instance.delete("testInstance", "key");
		String test = (String)instance.getCachedObject("testInstance", "key");
		assertNull("Object not removed", test);
		
	}

	@Test
	public void testInitCacheInstance() {
		JCacheMg cache = JCacheMg.getInstance();
		cache.initCacheInstance("test", 0);
	}

	@Test
	public void testGetInstance() {
		JCacheMg cache = JCacheMg.getInstance();
		assertNotNull("cache instance is null",cache);
	}

}
