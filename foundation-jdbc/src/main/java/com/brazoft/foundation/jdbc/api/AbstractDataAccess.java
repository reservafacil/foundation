package com.brazoft.foundation.jdbc.api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractDataAccess {

    /**
     * @param rs
     */
    protected void close(ResultSet rs) {
	try {
	    if (rs != null)
		rs.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    /**
     * @param st
     */
    protected void close(Statement st) {
	try {
	    if (st != null)
		st.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}