package com.brazoft.foundation.jdbc.api;

import java.sql.Connection;
import java.sql.SQLException;

import com.brazoft.foundation.jdbc.JDBCProperties;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public interface IDataSource
{
	/**
	 * @param jdbc
	 * @throws SQLException
	 */
	public void configure(JDBCProperties jdbc) throws SQLException;

	/**
	 * @return Returns the connection to database
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException;

	/**
	 * @param conn
	 * @throws SQLException
	 */
	public void release(Connection conn) throws SQLException;

	/**
	 * @param conn
	 * @throws SQLException
	 */
	public void commit(Connection conn) throws SQLException;

	/**
	 * @param conn
	 * @throws SQLException
	 */
	public void rollback(Connection conn) throws SQLException;
}