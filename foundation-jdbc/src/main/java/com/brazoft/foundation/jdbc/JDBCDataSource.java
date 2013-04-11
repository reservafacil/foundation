package com.brazoft.foundation.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.brazoft.foundation.jdbc.api.AbstractDataSource;
import com.brazoft.foundation.util.ConnectionProperties;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
class JDBCDataSource extends AbstractDataSource
{
	private JDBCProperties jdbc;
	
	public void configure(JDBCProperties jdbc)
	{
		try
		{
			DriverManager.registerDriver((Driver) Class.forName(jdbc.getProperties().getDriver()).newInstance());
			this.jdbc = jdbc;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException
	{
		ConnectionProperties properties;

		properties = this.jdbc.getProperties();
		
		if(this.jdbc.isOracle())
		{
			DriverManager.getConnection(properties.getUrl(), this.jdbc.toProperties());	
		}
		
		return DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword());
	}
}