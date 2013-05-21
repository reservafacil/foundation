package com.brazoft.foundation.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.brazoft.foundation.jdbc.api.IDataSource;
import com.brazoft.foundation.util.ExceptionHandler;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class Transactional {

    private IDataSource         datasource;

    private Connection          conn;

    private List<Transactional> toRelease;

    /**
	 * 
	 */
    protected Transactional() {
	this(JDBCProperties.newInstance(Transactional.DEFAULT_BUNDLE));
    }

    /**
     * @param bundle
     */
    protected Transactional(String bundle) {
	this(JDBCProperties.newInstance(bundle));
    }

    /**
     * @param url
     */
    protected Transactional(JDBCProperties url) {
	try {
	    this.datasource = DataSourceFactory.getImplementationFor(url);
	    this.toRelease = new ArrayList<Transactional>();
	} catch (SQLException e) {
	    ExceptionHandler.handleRuntime(e);
	}
    }

    /**
     * @param transaction
     */
    protected Transactional(Transactional transaction) {
	this.datasource = transaction.datasource;
	this.conn = transaction.conn;
    }

    /**
     * @throws SQLException
     */
    protected void loadConnection()
	throws SQLException {
	if (this.conn == null) {
	    this.conn = this.datasource.getConnection();
	    this.conn.setAutoCommit(false);
	}
    }

    /**
     * @param transactional
     */
    public void join(Transactional transactional) {
	this.toRelease.add(transactional);
    }

    /**
	 * 
	 */
    public void release() {
	try {
	    for (Transactional toRelease : this.toRelease) {
		toRelease.release();
	    }

	    this.toRelease.clear();

	    this.datasource.release(this.conn);
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    this.conn = null;
	}
    }

    /**
	 * 
	 */
    public void commit() {
	try {
	    for (Transactional toRelease : this.toRelease) {
		toRelease.commit();
	    }

	    this.datasource.commit(conn);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    /**
	 * 
	 */
    public void rollback() {
	try {
	    for (Transactional toRelease : this.toRelease) {
		toRelease.rollback();
	    }

	    this.datasource.rollback(conn);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    private static final String DEFAULT_BUNDLE = "javadb";
}