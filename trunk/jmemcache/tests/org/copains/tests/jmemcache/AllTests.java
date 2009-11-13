package org.copains.tests.jmemcache;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.copains.tests.jmemcache");
		//$JUnit-BEGIN$
		suite.addTestSuite(GarbageCollectionTest.class);
		suite.addTestSuite(JCacheMgTest.class);
		//$JUnit-END$
		return suite;
	}

}
