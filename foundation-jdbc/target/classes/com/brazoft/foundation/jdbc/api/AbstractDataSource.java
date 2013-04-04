package com.brazoft.foundation.jdbc.api;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractDataSource implements IDataSource
{
	public void commit(Connection connection) throws SQLException
	{
		if(this.isCurrent(connection))
		{
			connection.commit();
		}
	}

	public void rollback(Connection connection) throws SQLException
	{
		if(this.isCurrent(connection))
		{
			connection.rollback();
		}
	}

	public void release(Connection connection) throws SQLException
	{
		if(this.isCurrent(connection))
		{
			connection.close();
		}
	}
	
	/**
	 * @param connection
	 * @return Returns if connection is not null and is not closed
	 * @throws SQLException 
	 */
	protected boolean isCurrent(Connection connection) throws SQLException
	{
		return connection != null && !connection.isClosed();
	}
}