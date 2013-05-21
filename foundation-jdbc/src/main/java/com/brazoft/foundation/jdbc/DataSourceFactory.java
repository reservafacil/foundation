package com.brazoft.foundation.jdbc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.brazoft.foundation.jdbc.api.IDataSource;
import com.brazoft.foundation.util.ConnectionProperties;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class DataSourceFactory {

    private static final Map<String, IDataSource> cache = new HashMap<String, IDataSource>();

    /**
     * @param jdbc
     * @return Returns IDataSource implementation
     * @throws SQLException
     */
    public static IDataSource getImplementationFor(JDBCProperties jdbc)
	throws SQLException {
	IDataSource datasource;
	ConnectionProperties properties;

	try {
	    properties = jdbc.getProperties();

	    if (!DataSourceFactory.cache.containsKey(properties.getUrl())) {
		DataSourceFactory.cache.put(properties.getUrl(), (IDataSource)Class.forName(jdbc.getImplementationClass()).newInstance());
	    }

	    datasource = DataSourceFactory.cache.get(properties.getUrl());
	    datasource.configure(jdbc);

	    return datasource;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new SQLException(e.getMessage());
	}
    }

    /**
     * To use JDBC Driver Manager
     */
    public static final String JDBC_MANAGER      = JDBCDataSource.class.getName();

    /**
     * To use Apache Database Driver Manager Pool
     */
    public static final String POOL_MANAGER      = PoolableDataSource.class.getName();

    /**
     * To use J2EE Datasource API, when use this class, put in jdbc.jndi
     * property the jndi name for lookup
     */
    public static final String CONTAINER_MANAGER = ContainerDataSource.class.getName();
}