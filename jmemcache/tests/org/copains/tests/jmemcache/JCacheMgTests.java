package org.copains.tests.jmemcache;

import static org.junit.Assert.*;

import org.copains.jmemcache.CacheInstance;
import org.copains.jmemcache.JCacheMg;
import org.junit.Assert;
import org.junit.Test;

public class JCacheMgTests {

	@Test
	public void testGetCachedObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetCachedObjectNoInstanceInited() {
		JCacheMg.initInstance();
		JCacheMg cache = JCacheMg.getInstance();
		assertNull(cache.getCachedObject("test", "test"));
	}

	@Test
	public void testCacheStringJMemCacheable() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCacheStringObjectString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented"); // TODO
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
