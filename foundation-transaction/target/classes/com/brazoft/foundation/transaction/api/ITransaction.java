package com.brazoft.foundation.transaction.api;


/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public interface ITransaction
{
	/**
	 * 
	 */
	void begin();
	
	/**
	 * Commit the transaction
	 */
	void commit();
	
	/**
	 * Flush the transaction
	 */
	void flush();
	
	/**
	 * @return Returns if transaction is active
	 */
	boolean isActive();
	
	/**
	 * @param transaction
	 */
	void join(ITransaction transaction);
	
	/**
	 * Release the transaction
	 */
	void release();
	
	/**
	 * Rollback the transaction
	 */
	void rollback();
}