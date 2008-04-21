package org.copains.jmemcache.objects;

import org.copains.jmemcache.interfaces.JMemCacheable;

public class GenericMemCacheable implements JMemCacheable {

	private long expirationDate;
	
	private String key;
	
	private Object genericCachedObject;
	
	
	public GenericMemCacheable(Object o)
	{
		genericCachedObject = o;
	}
	
	public Object getCachedObject()
	{
		return (genericCachedObject);
	}

	public void setExpirationDate(long exp)
	{
		expirationDate = exp;
	}
	
	public long getExpirationDate() {
		return (expirationDate);
	}

	public void setKey(String key)
	{
		this.key = key;
	}
	
	public String getKey() {
		return (key);
	}

	public void update() {
		// TODO Auto-generated method stub

	}

}
