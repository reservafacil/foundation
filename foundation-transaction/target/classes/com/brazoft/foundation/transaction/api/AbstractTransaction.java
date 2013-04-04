package com.brazoft.foundation.transaction.api;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractTransaction implements ITransaction
{
	private List<ITransaction> joined;
	
	/**
	 * 
	 */
	public AbstractTransaction()
	{
		this.joined = new ArrayList<ITransaction>();
	}
	
	public void join(ITransaction transaction)
	{
		this.joined.add(transaction);
	}

	public void commit()
	{
		for(ITransaction tx : this.joined)
		{
			tx.commit();
		}
		
		this.doCommit();
	}

	public void release()
	{
		for(ITransaction tx : this.joined)
		{
			tx.release();
		}
		
		this.doRelease();
		this.joined.clear();
	}

	public void rollback()
	{
		for(ITransaction tx : this.joined)
		{
			tx.rollback();
		}
		
		this.doRollback();
	}
	
	/**
	 * 
	 */
	protected abstract void doCommit();
	
	/**
	 * 
	 */
	protected abstract void doRelease();
	
	/**
	 * 
	 */
	protected abstract void doRollback();
}