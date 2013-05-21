package com.brazoft.foundation.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.brazoft.foundation.jdbc.api.AbstractDataSource;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
class ContainerDataSource
    extends AbstractDataSource {

    private DataSource datasource;

    public void configure(JDBCProperties jdbc)
	throws SQLException {
	InitialContext ic = null;

	try {
	    ic = new InitialContext();
	    this.datasource = (DataSource)ic.lookup(jdbc.getProperties().getJndi());
	} catch (Exception e) {
	    throw new SQLException(e.getMessage());
	}
    }

    public Connection getConnection()
	throws SQLException {
	return this.datasource.getConnection();
    }
}