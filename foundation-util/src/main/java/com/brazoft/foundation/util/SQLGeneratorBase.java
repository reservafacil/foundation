package com.brazoft.foundation.util;

import java.util.List;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class SQLGeneratorBase {

    /**
	 * 
	 */
    public static final String      COMMA = ", ";

    private static SQLGeneratorBase instance;

    /**
	 * 
	 */
    protected SQLGeneratorBase() {
	super();
    }

    /**
     * @return Returns SQLGenerator.instance
     */
    public static SQLGeneratorBase getInstance() {
	if (SQLGeneratorBase.instance == null) {
	    SQLGeneratorBase.instance = new SQLGeneratorBase();
	}

	return SQLGeneratorBase.instance;
    }

    /**
     * @param entity
     * @param columns
     * @return Returns INSERT SQL Statement
     */
    @SuppressWarnings("unused")
    public String insertSQL(String entity, List<String> columns) {
	StringBuffer SQL;

	SQL = new StringBuffer("INSERT INTO ");
	SQL.append(entity).append(" (");

	for (String column : columns) {
	    SQL.append(column).append(SQLGeneratorBase.COMMA);
	}

	this.parse(SQL);
	SQL.append(") VALUES (");

	for (String column : columns) {
	    SQL.append("?, ");
	}

	this.parse(SQL);
	SQL.append(")");

	return SQL.toString();
    }

    /**
     * @param entity
     * @param indexes
     * @return Returns DELETE SQL Statement
     */
    public String deleteSQL(String entity, List<String> indexes) {
	StringBuffer SQL;

	SQL = new StringBuffer(this.deleteSQL(entity));

	for (String column : indexes) {
	    SQL.append(" AND ").append(column).append(" = ? ");
	}

	return SQL.toString().replaceFirst(" AND ", " WHERE ");
    }

    /**
     * @param entity
     * @param column
     * @return Returns DELETE SQL Statement
     */
    public String deleteBySQL(String entity, String column) {
	StringBuffer SQL;

	SQL = new StringBuffer(deleteSQL(entity));
	SQL.append(" WHERE ").append(column).append(" = ?");

	return SQL.toString();
    }

    /**
     * @param entity
     * @param columns
     * @param indexes
     * @return Returns update SQL
     */
    public String updateSQL(String entity, List<String> columns, List<String> indexes) {
	StringBuffer SQL;

	SQL = new StringBuffer("UPDATE ");
	SQL.append(entity).append(" SET ");

	for (String column : columns) {
	    SQL.append(column).append(" = ?").append(SQLGeneratorBase.COMMA);
	}

	this.parse(SQL);
	SQL.append(" WHERE ");

	for (String column : indexes) {
	    SQL.append(column).append(" = ? ").append("AND ");
	}

	this.remove(SQL, "AND ");

	return SQL.toString();
    }

    /**
     * @param entity
     * @param columns
     * @param indexes
     * @return Returns SELECT SQL Statement By Indexes WHERE clause
     */
    public String selectSQL(String entity, List<String> columns, List<String> indexes) {
	StringBuffer SQL;

	SQL = new StringBuffer(selectAllSQL(entity, columns));
	SQL.append(" WHERE ");

	for (String column : indexes) {
	    SQL.append(column).append(" = ? ").append("AND ");
	}

	this.remove(SQL, "AND ");

	return SQL.toString();
    }

    /**
     * @param entity
     * @param columns
     * @return Returns SELECT SQL Statement
     */
    public String selectAllSQL(String entity, List<String> columns) {
	StringBuffer SQL;

	SQL = new StringBuffer("SELECT ");

	for (String column : columns) {
	    SQL.append(column).append(SQLGeneratorBase.COMMA);
	}

	this.parse(SQL);
	SQL.append(" FROM ").append(entity);

	return SQL.toString();
    }

    /**
     * @param entity
     * @param columns
     * @param constraints
     * @return Returns Select all SQL
     */
    public String selectAllFKsSQL(String entity, List<String> columns, List<String> constraints) {
	StringBuffer SQL;

	SQL = new StringBuffer(selectAllSQL(entity, columns));
	SQL.append(" WHERE ");

	for (String column : constraints) {
	    SQL.append(column).append(" = ? ").append("AND ");
	}

	if (SQL.indexOf("?") > -1) {
	    this.remove(SQL, "AND ");
	}

	return SQL.toString();
    }

    /**
     * @param entity
     * @param columns
     * @param column
     * @return Returns Select SQL with where clause
     */
    public String selectBySQL(String entity, List<String> columns, String column) {
	StringBuffer SQL;

	SQL = new StringBuffer(selectAllSQL(entity, columns));
	SQL.append(" WHERE ").append(column).append(" = ?");

	return SQL.toString();
    }

    /**
     * @param entity
     * @return Returns DELETE Statement
     * 
     *         <b>CAUTION</b> It has no WHERE Clause, so it will clean table
     *         entirely
     */
    public String deleteSQL(String entity) {
	StringBuffer SQL;

	SQL = new StringBuffer("DELETE FROM ");
	SQL.append(entity);

	return SQL.toString();
    }

    /**
     * @param query
     */
    public void removeLastComma(StringBuffer query) {
	this.parse(query);
    }

    private void parse(StringBuffer SQL) {
	this.remove(SQL, SQLGeneratorBase.COMMA);
    }

    private void remove(StringBuffer SQL, String value) {
	if (SQL.toString().contains(value)) {
	    SQL.delete(SQL.lastIndexOf(value), SQL.length());
	}
    }
}