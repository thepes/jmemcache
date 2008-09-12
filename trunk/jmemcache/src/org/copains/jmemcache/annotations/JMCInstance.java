package org.copains.jmemcache.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is here to configure an object wich will be in cache
 * @author sdelaire
 * @version $Id$ 
 */
@Retention(value=RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JMCInstance {
	
	/*
	 * the instance name to use
	 */
	String instanceName();
	
	/* the lifetime of items in cache (in milliseconds) */
	int lifetime();
	
	/* max number of elements to store in this instance */
	int maxElements();

}
