package com.brazoft.foundation.util;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class ExceptionHandler
{
	public static void handleRuntime(Throwable e)
	{
		ExceptionHandler.print(e);
		throw new RuntimeException(e);
	}
	
	public static void handleRuntime(String message)
	{
		throw new RuntimeException(message);
	}
	
	private static void print(Throwable e)
	{
		e.printStackTrace();
	}
}