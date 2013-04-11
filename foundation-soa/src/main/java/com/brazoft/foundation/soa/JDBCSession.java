package com.brazoft.foundation.soa;

import java.sql.Connection;
import java.sql.SQLException;

import com.brazoft.foundation.jdbc.DataSourceFactory;
import com.brazoft.foundation.jdbc.JDBCProperties;
import com.brazoft.foundation.jdbc.api.AbstractDAO;
import com.brazoft.foundation.jdbc.api.IDataSource;
import com.brazoft.foundation.transaction.api.AbstractSession;
import com.brazoft.foundation.transaction.api.ITransaction;
import com.brazoft.foundation.util.ExceptionHandler;
import com.brazoft.foundation.util.api.IDAO;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class JDBCSession extends AbstractSession
{
	private IDataSource	datasource;

	private Connection	connection;

	/**
	 * 
	 */
	public JDBCSession()
	{
		super();
	}

	/**
	 * @param data
	 */
	public JDBCSession(Object data)
	{
		super(data);
	}
	
	@Override
	protected boolean isOpened()
	{
		return this.connection != null;
	}

	@Override
	protected void open()
	{
		try
		{
			if(this.datasource == null)
			{
				this.setProperties(JDBCProperties.newInstance(JDBCSession.DEFAULT_BUNDLE));
			}
			
			this.connection = this.datasource.getConnection();
			this.connection.setAutoCommit(false);
		}
		catch (SQLException e)
		{
			ExceptionHandler.handleRuntime(e);
		}
	}
	
	@Override
	protected void close()
	{
		try
		{
			this.datasource.release(this.connection);
		}
		catch (SQLException e)
		{
			ExceptionHandler.handleRuntime(e);
		}
		finally
		{
			this.connection = null;
			this.datasource = null;
		}
	}

	/**
	 * @param properties
	 */
	public void setProperties(JDBCProperties properties)
	{
		try
		{
			this.datasource = DataSourceFactory.getImplementationFor(properties);
		}
		catch (SQLException e)
		{
			ExceptionHandler.handleRuntime(e);
		}
	}
	
	/**
	 * @param dao
	 */
	protected void setDatasource(IDAO dao)
	{
		((AbstractDAO) dao).setConnection(this.connection);
	}
	
	/**
	 * @return Returns Connection
	 */
	Connection getConnection()
	{
		return connection;
	}
	
	/**
	 * @return Returns Datasource
	 */
	IDataSource getDatasource()
	{
		return datasource;
	}

	@Override
	protected ITransaction getTransactionInstance()
	{
		return new JDBCTransaction(this);
	}
	
	private static final String	DEFAULT_BUNDLE	= "javadb";
}