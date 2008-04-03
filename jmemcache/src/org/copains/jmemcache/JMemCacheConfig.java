package org.copains.jmemcache;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class JMemCacheConfig {
	
	/**
	 *  By default the config is loaded with the System Ressource Loader
	 */
	protected static void initConfig() throws IOException
	{
	    try {
	    	ClassLoader cl = Class.forName("org.copains.jmemcache.JMemCacheConfig").getClassLoader();
	    	InputStream is = cl.getResourceAsStream("/jmemcache.properties");
	    	parseConfig(is);
	    } catch (Exception e) {
	    	e.printStackTrace();
		}
	}
	
	private static boolean parseConfig(InputStream is) throws IOException
	{
	    if (null != is)
	    {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String currentInstanceName = null;
		    String line = br.readLine();
		    JCacheMg instance = JCacheMg.getInstance();
		    
		    while (line != null)
		    {
		    	if (!shouldIgnoreLine(line))
		    	{
		    		//System.out.println(line);
		    		if (line.startsWith("[") && line.endsWith("]"))
		    		{
		    			currentInstanceName = line.substring(1,line.length()-1);
		    		} else 
		    		{
		    			// parsing param name in a simple way
		    			if (line.startsWith("lifetime"))
		    			{
		    				Integer life = new Integer(line.substring(line.indexOf("=")+1));
		    				instance.initCacheInstance(currentInstanceName, life.intValue()*1000);
		    			}
		    			if (line.startsWith("max.elements"))
		    			{
		    				// TODO: max element is not yet implemented
		    			}
		    		}
		    	}
		    	
		    	line = br.readLine();
		    }
		    return (true);
	    }
		return (false);
	}
	
	/***
	 * method to check if the line is empty or a comment.
	 * @param line
	 * @return
	 */
	private static boolean shouldIgnoreLine(String line)
	{
		if (null == line)
			return (true);
		
		if (line.length() < 3)
			return (true);
		if (line.startsWith("!") || line.startsWith(";") || line.startsWith("#"))
			return (true);
		return (false);
	}
	
	/**
	 * this method can be used by external programs to load config from an external
	 * and special file (not in ClassPath for example)
	 * @param filename
	 * @return
	 */
	public static boolean loadConfig(String filename)
	{
//		File 
		return (false);
	}

}
