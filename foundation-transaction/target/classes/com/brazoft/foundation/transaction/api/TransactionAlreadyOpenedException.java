package com.brazoft.foundation.transaction.api;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class TransactionAlreadyOpenedException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * 
	 */
	public TransactionAlreadyOpenedException()
	{
		super();
	}

	/**
	 * @param message
	 */
	public TransactionAlreadyOpenedException(String message)
	{
		super(message);
	}
}