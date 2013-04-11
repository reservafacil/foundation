package com.brazoft.foundation.io;

import java.io.BufferedWriter;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class StringWriter extends BufferedWriter
{
	private java.io.StringWriter	output;
	
	/**
	 * @return Returns StringWriter
	 */
	public static StringWriter newInstance()
	{
		return new StringWriter(new java.io.StringWriter());
	}
	
	/**
	 * @return Returns content data
	 * @throws java.io.IOException 
	 */
	public String getData() throws java.io.IOException
	{
		String data;
		
		try
		{
			this.flush();
			
			data = this.output.toString();
			
			return data;
		}
		finally
		{
			this.close();
		}
	}

	/**
	 * @param output
	 */
	protected StringWriter(java.io.StringWriter output)
	{
		super(output);
		this.output = output;
	}
}
