package com.brazoft.foundation.io;

import java.io.File;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class FileFilter implements java.io.FileFilter
{
	public boolean accept(File f)
	{
		return f.isFile();
	}
}