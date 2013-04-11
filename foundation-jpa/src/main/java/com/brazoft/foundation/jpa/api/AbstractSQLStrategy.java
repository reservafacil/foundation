package com.brazoft.foundation.jpa.api;

import java.util.List;

import org.hibernate.Query;

import com.brazoft.foundation.commons.Validator;
import com.brazoft.foundation.util.SQLGeneratorBase;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractSQLStrategy<T> extends AbstractQueryStrategy<T>
{
	private StringBuffer query;

	/**
	 * @param query
	 */
	public AbstractSQLStrategy(StringBuffer query)
	{
		this.query = query;
	}
	
	@Override
	public T doLoad(T search)
	{
		return (T) this.createQuery(search).uniqueResult();
	}

	/**
	 * @param search
	 * @param orderBy
	 * @return Returns List<T>
	 */
	public List<T> doFindAsc(T search, String... orderBy)
	{
		if (!Validator.isEmptyOrNull(orderBy))
		{
			this.appendOrderBy(orderBy);
			this.query.append(" DESC");
		}

		return this.doFind(this.createQuery(search));
	}

	/**
	 * @param search
	 * @param orderBy
	 * @return Returns List<T>
	 */
	public List<T> doFindDesc(T search, String... orderBy)
	{
		if (!Validator.isEmptyOrNull(orderBy))
		{
			this.appendOrderBy(orderBy);
			this.query.append(" ASC");
		}

		return this.doFind(this.createQuery(search));
	}

	/**
	 * @param search
	 * @return Returns List<T>
	 */
	public List<T> doFind(T search)
	{
		return this.doFind(this.createQuery(search));
	}

	/**
	 * @return Returns Query
	 */
	protected StringBuffer getQuery()
	{
		return query;
	}

	/**
	 * @param query
	 * @return Returns List<T>
	 */
	private List<T> doFind(Query query)
	{
		return query.list();
	}
	
	private void appendOrderBy(String... orderBy)
	{
		this.query.append(" ORDER BY ");
		for (String fieldName : orderBy)
		{
			this.query.append(fieldName);
			this.query.append(SQLGeneratorBase.COMMA);
		}

		SQLGeneratorBase.getInstance().removeLastComma(this.query);
	}
	
	/**
	 * @param search
	 * @return Returns Query from search
	 */
	protected abstract Query createQuery(T search);
}