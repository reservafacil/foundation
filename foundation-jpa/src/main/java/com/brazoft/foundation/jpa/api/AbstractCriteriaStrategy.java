package com.brazoft.foundation.jpa.api;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractCriteriaStrategy<T> extends AbstractQueryStrategy<T>
{
	@Override
	public T doLoad(T search)
	{
		Criteria query;
		
		query = this.createCriteria(search);
		
		return (T) query.uniqueResult();
	}
	
	public List<T> doFind(T search)
	{
		Criteria query;
		
		query = this.createCriteria(search);
		
		return query.list();
	}
	
	@Override
	public List<T> doFindAsc(T search, String... orderBy)
	{
		Criteria query;

		query = this.createCriteria(search);

		for (String column : orderBy)
		{
			query.addOrder(Order.asc(column));
		}

		return query.list();
	}
	
	@Override
	public List<T> doFindDesc(T search, String... orderBy)
	{
		Criteria query;

		query = this.createCriteria(search);

		for (String column : orderBy)
		{
			query.addOrder(Order.desc(column));
		}

		return query.list();
	}
	
	/**
	 * This method creates a criteria based on search object
	 * 
	 * @param search
	 * @return Returns Criteria
	 */
	protected Criteria createCriteria(T search)
	{
		Criteria query;

		query = this.init(search);
		
		if(search != null)
		{
			this.setCriteria(search, query);
		}

		return query;
	}
	
	private Criteria init(T search)
	{
		if(search == null)
		{
			return super.getContext().createCriteria(super.getDAO().getEntityClass());
		}
		
		return super.getContext().createCriteria(search.getClass());
	}
	
	/**
	 * @param search
	 * @param query
	 */
	protected abstract void setCriteria(T search, Criteria query);
}