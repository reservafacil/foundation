package com.brazoft.foundation.jpa.api;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.brazoft.foundation.jpa.DefaultCriteriaStrategy;
import com.brazoft.foundation.util.ExceptionHandler;
import com.brazoft.foundation.util.api.IDAO;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T> implements IDAO
{
	private Session context;

	private AbstractQueryStrategy<T> strategy;
	
	/**
	 * 
	 */
	public AbstractDAO()
	{
		this.setStrategy(new DefaultCriteriaStrategy<T>());
	}

	/**
	 * @param strategy
	 */
	public void setStrategy(AbstractQueryStrategy<T> strategy)
	{
		if(this.strategy != null)
		{
			strategy.setContext(this.strategy.getContext());
		}
		this.strategy = strategy;
		this.strategy.setDAO(this);
	}

	/**
	 * @param SQL
	 */
	protected void doExecute(String SQL)
	{
		this.context.createSQLQuery(SQL).executeUpdate();
	}

	/**
	 * @param bean
	 */
	protected void doInsert(T bean)
	{
		try
		{
			this.context.save(bean);
		}
		finally
		{
			this.flush();
		}
	}

	/**
	 * @param bean
	 */
	protected void doUpdate(T bean)
	{
		try
		{
			this.context.update(bean);
		}
		finally
		{
			this.flush();
		}
	}

	/**
	 * @param bean
	 */
	protected void doInsertOrUpdate(T bean)
	{
		try
		{
			this.context.saveOrUpdate(bean);
		}
		finally
		{
			this.flush();
		}
	}

	/**
	 * @param search
	 */
	protected void doDelete(T search)
	{
		try
		{
			for (T object : this.doFind(search))
			{
				this.context.delete(object);
			}
		}
		finally
		{
			this.flush();
		}
	}

	/**
	 * @param key
	 * @return Returns object loaded
	 */
	protected T doLoad(Serializable key)
	{
		try
		{
			return (T) this.context.load(this.getEntityClass(), key);
		}
		finally
		{
			this.flush();
		}
	}
	
	protected <E> E doLoad(Class<E> entityClass, Serializable key)
	{
		try
		{
			return (E) this.context.load(this.getEntityClass(), key);
		}
		finally
		{
			this.flush();
		}
	}
	
	protected T doSearch(T search)
	{
		try
		{
			return this.strategy.doLoad(search);
		}
		finally
		{
			this.flush();
		}
	}

	/**
	 * @param search
	 * @return Returns List<T>
	 */
	protected List<T> doFind(T search)
	{
		try
		{
			return (List<T>) this.strategy.doFind(search);
		}
		finally
		{
			this.flush();
		}
	}

	/**
	 * @param search
	 * @param orderBy
	 * @return Returns List<T>
	 */
	protected List<T> doFindDesc(T search, String... orderBy)
	{
		try
		{
			return (List<T>) this.strategy.doFindDesc(search, orderBy);
		}
		finally
		{
			this.flush();
		}
	}

	/**
	 * @param search
	 * @param orderBy
	 * @return Returns List<T>
	 */
	protected List<T> doFindAsc(T search, String... orderBy)
	{
		try
		{
			return (List<T>) this.strategy.doFindAsc(search, orderBy);
		}
		finally
		{
			this.flush();
		}
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(Session context)
	{
		this.context = context;
		this.strategy.setContext(context);
	}

	/**
	 * 
	 */
	protected void flush()
	{
		this.context.flush();
		this.context.clear();
	}
	
	/**
	 * @return Returns Class<?>
	 */
	protected Class<?> getEntityClass()
	{
		try
		{
			String name = this.getClass().getGenericSuperclass().toString();
			if(!name.contains("<"))
			{
				name = this.getClass().getSuperclass().getGenericSuperclass().toString();
			}
			name = name.substring(name.indexOf("<") + 1);
			name = name.substring(0, name.indexOf(">"));

			return Class.forName(name);
		}
		catch (ClassNotFoundException e)
		{
			ExceptionHandler.handleRuntime(e);

			return null;
		}
	}
}