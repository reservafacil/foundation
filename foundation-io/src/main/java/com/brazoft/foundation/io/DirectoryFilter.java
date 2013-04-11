package com.brazoft.foundation.io;

import java.io.File;
import java.io.FileFilter;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class DirectoryFilter implements FileFilter
{
	public boolean accept(File f)
	{
		return f.isDirectory();
	}
}