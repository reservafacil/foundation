package com.brazoft.foundation.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class FileUtil
{
	/**
	 * @param path
	 * @return Returns file name
	 */
	public static String extractFileName(String path)
	{
		path = FileUtil.toRelativePath(path);
		
		return path.substring(path.lastIndexOf("/") + 1);
	}
	
	/**
	 * @param fileName
	 * @return Returns file extension
	 */
	public static String extractExtension(String fileName)
	{
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	/**
	 * @param parent
	 * @return Returns List<File>
	 */
	public static List<File> doChildren(File parent)
	{
		List<File> list;
		
		list = new ArrayList<File>();
		
		if(parent.isDirectory())
		{
			FileUtil.doChildren(parent, list);
		}
		
		return list;
	}
	
	private static void doChildren(File parent, List<File> list)
	{
		for(File child : parent.listFiles())
		{
			if(child.isDirectory())
			{
				FileUtil.doChildren(child, list);
				continue;
			}
			
			list.add(child);
		}
	}
	
	/**
	 * @param path
	 * @return Returns 
	 */
	private static String toRelativePath(String path)
	{
		return path.replace("\\", "/");
	}
}