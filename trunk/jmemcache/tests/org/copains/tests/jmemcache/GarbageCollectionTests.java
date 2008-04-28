package org.copains.tests.jmemcache;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.copains.jmemcache.JCacheMg;
import org.junit.Test;

public class GarbageCollectionTests extends TestCase {

	private static final String INSTANCE_NAME = "testInstance";
	
	@Test
	public void testGc_Full() {
		JCacheMg instance = JCacheMg.getInstance();
		Object o = new Object();
		instance.cache(INSTANCE_NAME, o, "1");
		for (int i = 0 ; i < 15 ; i++)
		{
			o = new Object();
			instance.cache(INSTANCE_NAME, o, ""+i);			
		}
		int count = instance.countElements(INSTANCE_NAME);
		assertTrue("Problem with GC count = "+count
				,count == 10);
	}
	
	/**
	 * checking if last inserted element is still in the cache
	 */
	@Test
	public void testGc_NotLoosingOldest() {
		JCacheMg instance = JCacheMg.getInstance();
		
		// cleaning cache
		instance.truncateCacheInstance(INSTANCE_NAME);
		Object o = new Object();
		instance.cache(INSTANCE_NAME, o, "1");
		int i;
		for (i = 0 ; i < 15 ; i++)
		{
			o = new Object();
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
			instance.cache(INSTANCE_NAME, o, ""+i);			
		}
		assertNotNull("Last inserted element not in cache", 
				instance.getCachedObject(INSTANCE_NAME, ""+(i-1)));
	}
	
	
	@Test
	public void testGc_cleaningOldElts()
	{
		JCacheMg instance = JCacheMg.getInstance();
		
		// cleaning cache
		instance.truncateCacheInstance(INSTANCE_NAME);
		Object o = new Object();
		instance.cache(INSTANCE_NAME, o, "1");
		int i;
		for (i = 0 ; i < 15 ; i++)
		{
			o = new Object();
			instance.cache(INSTANCE_NAME, o, ""+i);			
		}
		try {
			Thread.sleep(11000);
		} catch (Exception e) {
		}
		o = new Object();
		assertNotNull("cache succedeed", instance.cache(INSTANCE_NAME, o, "test"));
	}
	
}
