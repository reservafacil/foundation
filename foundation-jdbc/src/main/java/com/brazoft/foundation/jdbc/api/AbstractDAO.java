package com.brazoft.foundation.jdbc.api;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.brazoft.foundation.commons.Validator;
import com.brazoft.foundation.jdbc.JDBCUtil;
import com.brazoft.foundation.util.Converter;
import com.brazoft.foundation.util.ExceptionHandler;
import com.brazoft.foundation.util.api.IDAO;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractDAO implements IDAO
{
	/**
	 * Database Connection
	 */
	protected Connection	conn;
	
	/**
	 * @param conn
	 *            Database connection to set.
	 */
	public void setConnection(Connection conn)
	{
		this.conn = conn;
	}
	
	/**
	 * @return Returns Connection
	 */
	protected Connection getConnection()
	{
		return conn;
	}
	
	/**
     * @param rs
     */
    protected void close(ResultSet rs)
    {
        try
        {
            if(rs != null)
                rs.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * @param st
     */
    protected void close(Statement st)
    {
        try
        {
            if(st != null)
                st.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
	
	/**
	 * @return Returns Statement
	 * @throws SQLException 
	 */
	protected Statement createStatement()
	{
		try
		{
			return this.conn.createStatement();
		}
		catch (SQLException e)
		{
			ExceptionHandler.handleRuntime(e);
			return null;
		}
	}
	
	/**
	 * @param sql 
	 * @return Returns PreparedStatement
	 * @throws SQLException 
	 */
	protected PreparedStatement prepareStatement(String sql)
	{
		try
		{
			return this.conn.prepareStatement(sql);
		}
		catch (SQLException e)
		{
			ExceptionHandler.handleRuntime(e);
			return null;
		}
	}
	
	/**
	 * @param sql
	 * @param limitRows
	 * @return Returns PreparedStatement
	 * @throws SQLException
	 */
	protected PreparedStatement prepareStatement(String sql, int limitRows)
	{
		StringBuffer SQL;
		
		try
		{
			SQL = new StringBuffer(sql);
			this.limitTo(SQL, limitRows);
			
			return this.prepareStatement(SQL.toString());
		}
		catch (SQLException e)
		{
			ExceptionHandler.handleRuntime(e);
			return null;
		}
	}
	
	/**
	 * @param sql
	 * @return Returns CallableStatement
	 * @throws SQLException
	 */
	protected CallableStatement prepareCall(String sql)
	{
		try
		{
			return this.conn.prepareCall(sql);
		}
		catch (SQLException e)
		{
			ExceptionHandler.handleRuntime(e);
			return null;
		}
	} 

	/**
	 * Set null if parameter value is zero.
	 * 
	 * @param parameter
	 * @param value
	 * @param st
	 * @throws SQLException
	 */
	protected void setInt(int parameter, int value, PreparedStatement st)
	{
		try
		{
			if(value == 0)
			{
				this.setNull(parameter, st);
	
				return;
			}
	
			st.setInt(parameter, value);
		}
		catch(SQLException e)
		{
			ExceptionHandler.handleRuntime(e);
		}
	}
	
	/**
	 * @param parameter
	 * @param value
	 * @param st
	 * @throws SQLException
	 */
	protected void setBoolean(int parameter, Boolean value, PreparedStatement st)
	{
		try
		{
			st.setInt(parameter, Converter.toint(value));
		}
		catch (SQLException e)
		{
			ExceptionHandler.handleRuntime(e);
		}
	}
	
	/**
	 * @param columnName
	 * @param rs
	 * @return Returns Boolean value
	 * @throws SQLException
	 */
	protected Boolean getBoolean(String columnName, ResultSet rs)
	{
		try
		{
			return Converter.toBoolean(rs.getInt(columnName));
		}
		catch (SQLException e)
		{
			ExceptionHandler.handleRuntime(e);
			return null;
		}
	}
	
	/**
	 * @param parameter
	 * @param st
	 * @throws SQLException 
	 */
	protected void setNull(int parameter, PreparedStatement st)
	{
		try
		{
			st.setNull(parameter, Types.INTEGER);
		}
		catch (SQLException e)
		{
			ExceptionHandler.handleRuntime(e);
		}
	}

	/**
	 * @param value
	 * @return Returns if value can be applied to filter
	 */
	protected boolean isFilter(Object value)
	{
		if(value == null)
		{
			return false;
		}

		return !Validator.isEmptyOrNullOrZero(value.toString());
	}

	/**
	 * @param ps
	 * 
	 * @throws SQLException
	 */
	protected void execute(PreparedStatement ps)
	{
		try
		{
			if(ps.executeUpdate() == 0)
			{
				throw new RuntimeException("Update count is not 1 or more");
			}
		}
		catch(SQLException e)
		{
			ExceptionHandler.handleRuntime(e);
		}
	}
	
	/**
	 * @param SQL
	 * @param numberOfRows
	 * @throws SQLException 
	 */
	private void limitTo(StringBuffer SQL, int numberOfRows) throws SQLException
	{
		if(JDBCUtil.isSQLServer(this.conn))
		{
			//select top 10
			SQL.insert("SELECT ".length(), "TOP " + numberOfRows);
		}
		
		if(JDBCUtil.isMySQL(this.conn) || JDBCUtil.isPostgre(this.conn))
		{
			//limit 10
			SQL.append(" LIMIT ").append(numberOfRows);
		}
		
		if(JDBCUtil.isOracle(this.conn))
		{
			//WHERE rownum <= 10
			throw new RuntimeException(" Oracle limitTo not implemented yet. Please contact support.");
		}
		
		if(JDBCUtil.isDB2(this.conn))
		{
			//fetch first 10 rows only
			SQL.append(" FETCH FIRST ").append(numberOfRows).append(" ROWS ONLY");
		}
	}
}