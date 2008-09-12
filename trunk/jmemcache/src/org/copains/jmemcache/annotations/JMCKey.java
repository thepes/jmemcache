package org.copains.jmemcache.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * the key used to store and retrieve objects in cache
 * @author sdelaire
 * @version $Id$
 */
@Retention(value=RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JMCKey {

	
	
}
