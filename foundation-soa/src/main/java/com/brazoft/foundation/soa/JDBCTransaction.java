package com.brazoft.foundation.soa;

import java.sql.Connection;
import java.sql.SQLException;

import com.brazoft.foundation.jdbc.api.IDataSource;
import com.brazoft.foundation.transaction.api.AbstractTransaction;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class JDBCTransaction
    extends AbstractTransaction {

    private IDataSource datasource;

    private Connection  connection;

    private boolean     active;

    /**
     * @param session
     */
    public JDBCTransaction(JDBCSession session) {
	this.datasource = session.getDatasource();
	this.connection = session.getConnection();
    }

    /**
     * @param transaction
     */
    public JDBCTransaction(JDBCTransaction transaction) {
	this.datasource = transaction.datasource;
	this.connection = transaction.connection;
    }

    public void begin() {
	this.active = true;
    }

    public boolean isActive() {
	return this.active;
    }

    public void flush() {
	return;
    }

    /**
	 * 
	 */
    protected void doRelease() {
	this.active = false;
    }

    /**
	 * 
	 */
    protected void doCommit() {
	try {
	    this.datasource.commit(this.connection);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    /**
	 * 
	 */
    protected void doRollback() {
	try {
	    this.datasource.rollback(this.connection);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}