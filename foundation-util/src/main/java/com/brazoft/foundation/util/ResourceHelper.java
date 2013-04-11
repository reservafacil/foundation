package com.brazoft.foundation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class ResourceHelper
{
	/**
	 * @param url
	 * 
	 * Example 
	 * url = http://www.brazoft.com.br
	 * change to http://www.brazoft.com.br/
	 * 
	 */
	public static void formatURL(StringBuffer url)
	{
		if (url.charAt(url.length() - 1) != '/')
		{
			url.append("/");
		}
	}
	
	public static InputStream getStream(String path)
	{
		InputStream input = null;
		File file;
		
		try
		{
			file = new File(path);
			if(file.exists())
			{
				return new FileInputStream(file);
			}
			
			input = ResourceHelper.getClassPath(path);
			
			if(input == null)
			{
				input = ResourceHelper.getMetaResource(path);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return input;
	}
	
	public static URL getResource(String path)
	{
		URL url;
		
		try
		{
			url = ResourceHelper.getMetaResourceAsURL(path);
			
			if(url == null)
			{
				url = ResourceHelper.getClassPathAsURL(path);
			}
			
			if(url == null)
			{
				url = new URL("jndi:" + path);
			}
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
			url = null;
		}
		
		return url;
	}
	
	public static InputStream getMetaResource(String path)
	{
		return ResourceHelper.getClassPath(ResourceHelper.META_INF + path);
	}
	
	public static URL getMetaResourceAsURL(String path)
	{
		return ResourceHelper.getClassPathAsURL(ResourceHelper.META_INF + path);
	}
	
	public static Enumeration<URL> getMetaResources(String path) throws IOException
	{
		return ResourceHelper.getClassPathResources(ResourceHelper.META_INF + path);	
	}
	
	public static InputStream getClassPath(String path)
	{
		return ResourceHelper.class.getClassLoader().getResourceAsStream(path);
	}
	
	public static URL getClassPathAsURL(String path)
	{
		return ResourceHelper.class.getClassLoader().getResource(ResourceHelper.parse(path));
	}
	
	public static Enumeration<URL> getClassPathResources(String path) throws IOException
	{
		return ResourceHelper.class.getClassLoader().getResources(path);	
	}
	
	protected static String parse(String path)
	{
		if(path.startsWith("/"))
		{
			path = path.substring(1);
		}
		
		path = path.replace("//", "/");
		
		return path;
	}
	
	private static final String META_INF = "META-INF/";
}