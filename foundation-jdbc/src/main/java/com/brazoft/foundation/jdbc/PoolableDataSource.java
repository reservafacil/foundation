package com.brazoft.foundation.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

import com.brazoft.foundation.jdbc.api.AbstractDataSource;
import com.brazoft.foundation.util.ConnectionProperties;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 * 
 */
public class PoolableDataSource extends AbstractDataSource
{
	private PoolingDataSource datasource;
	
	public void configure(JDBCProperties jdbc) throws SQLException
	{
		GenericObjectPool pool;
		ConnectionFactory factory;
		ConnectionProperties properties;
		
		try
		{
			properties = jdbc.getProperties();
			
			Class.forName(properties.getDriver());
			
			pool = new GenericObjectPool(null);
			pool.setMaxActive(jdbc.getMaxActive());
			pool.setMaxIdle(jdbc.getMaxIdle());
			pool.setMaxWait(jdbc.getMaxWait());
			
			if(jdbc.isOracle())
			{
				factory = new DriverManagerConnectionFactory(properties.getUrl(), jdbc.toProperties());
			}
			else
			{
				factory = new DriverManagerConnectionFactory(properties.getUrl(), properties.getUser(), properties.getPassword());
			}
			
			new PoolableConnectionFactory(factory, pool, null, null, false, true);
			
			this.datasource = new PoolingDataSource(pool);
		}
		catch (Exception e)
		{
			throw new SQLException(e.getMessage());
		}
	}

	public Connection getConnection() throws SQLException
	{
		return this.datasource.getConnection();
	}
}