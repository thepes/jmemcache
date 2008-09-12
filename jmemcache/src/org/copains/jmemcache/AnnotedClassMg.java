package org.copains.jmemcache;

import org.copains.jmemcache.annotations.JMCInstance;
import org.copains.jmemcache.exceptions.AnnotationExceptionsTypes;
import org.copains.jmemcache.exceptions.JMemCacheAnnotationException;

/**
 * singleton for annoted class works (use some cache to avoid too much reflection)
 * @author sdelaire
 *
 */
public class AnnotedClassMg {
	
	private static AnnotedClassMg instance = null;
	
	private Object mutex = new Object();
	
	public AnnotedClassMg()
	{
		
	}
	
	public boolean cache(Object o) throws JMemCacheAnnotationException
	{
		// TODO: handle cache for classes / annotations / keys
		System.out.println("Dans cache annoted");
		JMCInstance instance = o.getClass().getAnnotation(JMCInstance.class);
		if (null == instance)
			throw (new JMemCacheAnnotationException(AnnotationExceptionsTypes.MISSING_INSTANCE));
		System.out.println(instance.instanceName());
		return (false);
	}
	
	public static synchronized AnnotedClassMg getInstance()
	{
		if (null == instance)
			instance = new AnnotedClassMg();
		return (instance);
	}

}
