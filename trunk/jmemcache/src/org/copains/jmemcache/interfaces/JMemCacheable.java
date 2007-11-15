package org.copains.jmemcache.interfaces;

public interface JMemCacheable {

	public String getKey();
	
	public long getExpirationDate();
	public void setExpirationDate(long expirationDate);
	
	public Object getCachedObject();

	/**
	 * this method should implement object update in cache
	 *
	 */
	public void update();
	
}
