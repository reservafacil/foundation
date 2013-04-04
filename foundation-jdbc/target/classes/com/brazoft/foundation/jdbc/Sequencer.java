package com.brazoft.foundation.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import com.brazoft.foundation.jdbc.api.AbstractDataAccess;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class Sequencer extends AbstractDataAccess
{
	private String	table;

	private String	column;

	private String	name;
	
	private boolean selfGenerator;
	
	private int id;

	/**
	 * @param table
	 * @param column
	 * @param name
	 */
	public Sequencer(String table, String column, String name)
	{
		this(table, column, name, false);
	}
	
	/**
	 * @param table
	 * @param column
	 * @param name
	 * @param selfGenerator
	 */
	public Sequencer(String table, String column, String name, boolean selfGenerator)
	{
		this.table = table;
		this.column = column;
		this.name = name;
		this.selfGenerator = selfGenerator;
	}
	
	/**
	 * @param connection
	 * @param fields
	 * @return Returns PreparedStatement
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(Connection connection, String[] fields) throws SQLException
	{
		String SQL;
		
		SQL = this.generateSQL(connection, fields);
		
		if(JDBCUtil.isMySQL(connection))
		{
			return connection.prepareStatement(SQL, this.getIndex(fields));
		}
		
		return connection.prepareStatement(SQL);
	}
	
	/**
	 * @param ps
	 * @return Returns generated key
	 * @throws SQLException
	 */
	public int getGeneratedKey(PreparedStatement ps) throws SQLException
	{
		ResultSet rs;
		StringBuffer SQL;
		Connection connection;
		Statement st;
		
		rs = null;
		st = null;
		
		try
		{
			if(this.selfGenerator)
			{
				return this.id;
			}
			
			connection = ps.getConnection();
			if(JDBCUtil.isMySQL(connection))
			{
				rs = ps.getGeneratedKeys();
			}
			else
			{
				SQL = new StringBuffer();
				SQL.append("SELECT ");
				
				if(JDBCUtil.isSQLServer(connection))
				{
					SQL.append("Max(").append(this.column).append(")");
				}
				else if(JDBCUtil.isOracle(connection))
				{
					SQL.append(this.name).append(".currVal");
				}
				else if(JDBCUtil.isDB2(connection))
				{
					SQL.append("currval for ").append(this.name);
				}
				else if(JDBCUtil.isPostgre(connection))
				{
					SQL.append("currval('").append(this.name).append("')");
				}
				
				SQL.append(" FROM ").append(this.table);
				
				super.close(ps);
				
				st = connection.createStatement();
				rs = st.executeQuery(SQL.toString());
			}
			
			if(rs.next())
			{
				return rs.getInt(1);
			}
			
			return 0;
		}
		finally
		{
			super.close(rs);
			super.close(st);
		}
	}
	
	/**
	 * @param fields
	 * @return Returns index of Primary Key
	 */
	protected int getIndex(String[] fields)
	{
		return Arrays.asList(fields).indexOf(this.column);
	}

	/**
	 * @param connection
	 * @param fields
	 * @return Returns SQL Insert using sequence
	 * @throws SQLException
	 */
	protected String generateSQL(Connection connection, String[] fields) throws SQLException
	{
		StringBuffer SQL;
		
		SQL = new StringBuffer("INSERT INTO ");
		if(JDBCUtil.isMySQL(connection))
		{
			SQL.append(connection.getCatalog()).append(connection.getMetaData().getCatalogSeparator()).append(this.table); 
		}
		else
		{
			SQL.append(this.table);
		}
		SQL.append("(");

		for (String field : fields)
		{
			SQL.append(field).append(", ");
		}

		if(this.isAutoIncrement(connection))
		{
			SQL.delete(SQL.indexOf(this.column.concat(", ")), SQL.indexOf(this.column.concat(", ")) + this.column.concat(", ").length());
		}
		
		if(SQL.lastIndexOf(",") == SQL.length() - 2)
		{
			SQL.delete(SQL.lastIndexOf(","), SQL.length());
		}

		SQL.append(")VALUES(");

		for (String field : fields)
		{
			if(field.equals(this.column))
			{
				if(this.selfGenerator)
				{
					SQL.append(this.nextId(connection)).append(", ");
					continue;
				}
				if(JDBCUtil.isOracle(connection))
				{
					SQL.append(this.name).append(".nextVal").append(", ");
				}
				else if(JDBCUtil.isDB2(connection))
				{
					SQL.append("nextval for ").append(this.name).append(", ");
				}
				else if(JDBCUtil.isPostgre(connection))
				{
					SQL.append("nextval('").append(this.name).append("')").append(", ");
				}
			}
			else
			{
				SQL.append("?").append(", ");
			}
		}

		SQL.delete(SQL.length() - 2, SQL.length());

		SQL.append(")");

		return SQL.toString();
	}
	
	private int nextId(Connection connection) throws SQLException
	{
		ResultSet rs;
		StringBuffer SQL;
		Statement st;
		
		rs = null;
		st = null;
		
		try
		{
			SQL = new StringBuffer("SELECT Max(");
			SQL.append(this.column).append(")");
			SQL.append(" FROM ").append(this.table);
				
			st = connection.createStatement();
			rs = st.executeQuery(SQL.toString());
			
			if(rs.next())
			{
				this.id = rs.getInt(1) + 1;
				
				return this.id;
			}
			
			return 0;
		}
		finally
		{
			super.close(rs);
			super.close(st);
		}
	}

	/**
	 * @param connection
	 * @return Returns if connection correspond to MS SQL Server
	 * @throws SQLException
	 */
	public boolean isAutoIncrement(Connection connection) throws SQLException
	{
		return (JDBCUtil.isSQLServer(connection) || JDBCUtil.isMySQL(connection)) && !this.selfGenerator;
	}	
}