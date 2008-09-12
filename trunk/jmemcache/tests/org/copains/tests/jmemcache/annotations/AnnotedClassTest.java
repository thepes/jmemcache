package org.copains.tests.jmemcache.annotations;

import junit.framework.TestCase;

import org.copains.jmemcache.JCacheMg;
import org.copains.tests.jmemcache.objects.AnnotedWithoutKey;
import org.copains.tests.jmemcache.objects.NotAnnoted;
import org.junit.Test;


public class AnnotedClassTest extends TestCase {
	
	@Test
	public void testInstanceAnnotationMissing()
	{
		NotAnnoted na = new NotAnnoted();
		try {
			JCacheMg.getInstance().cacheAnnoted(na);
			fail("No Exception returned");
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testKeyAnnotationMissing()
	{
		AnnotedWithoutKey na = new AnnotedWithoutKey();
		try {
			JCacheMg.getInstance().cacheAnnoted(na);
		} catch (Exception e) {
			fail("exception returned");
		}
	}
}
