package com.brazoft.foundation.transaction;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class TransactionState
{
	private TransactionState.State state;

	/**
	 * @param state 
	 */
	public TransactionState(TransactionState.State state)
	{
		super();
		this.state = state;
	}
	
	/**
	 * 
	 */
	public TransactionState()
	{
		this(TransactionState.State.UNKNOW);
	}

	/**
	 * @return Returns TransactionState.EState
	 */
	public TransactionState.State getState()
	{
		return state;
	}
	
	/**
	 * @param state
	 */
	public void setState(TransactionState.State state)
	{
		this.state = state;
	}
	
	/**
	 * @author Anderson Braz - anderson.braz@brazoft.com.br
	 */
	public enum State
	{
		/**
		 * 
		 */
		UNKNOW, 
		
		/**
		 * 
		 */
		ERROR, 
		
		/**
		 * 
		 */
		VALID;
	}
}