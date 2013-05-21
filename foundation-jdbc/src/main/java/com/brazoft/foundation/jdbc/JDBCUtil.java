package com.brazoft.foundation.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.brazoft.foundation.util.JDBCVendor;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class JDBCUtil {

    /**
     * @param vendor
     * @param host
     * @param port
     * @param database
     * @param user
     * @param password
     * @return Returns ConnectionString
     */
    public static JDBCProperties toJDBCProperties(JDBCVendor vendor, String host, int port, String database, String user, String password) {
	return JDBCProperties.newInstance(JDBCUtil.toProperties(vendor, host, port, database, user, password));
    }

    /**
     * @param vendor
     * @param jndi
     * @return Returns ConnectionString
     */
    public static JDBCProperties toJDBCProperties(JDBCVendor vendor, String jndi) {
	return JDBCProperties.newInstance(JDBCUtil.toProperties(jndi));
    }

    /**
     * @param jndi
     * @return Returns Properties
     */
    public static Properties toProperties(String jndi) {
	Properties properties;

	properties = new Properties();

	properties.put(JDBCProperties.KEY_JNDI, jndi);
	properties.put(JDBCProperties.KEY_FACTORY_CLASS, DataSourceFactory.CONTAINER_MANAGER);

	return properties;
    }

    /**
     * @param vendor
     * @param host
     * @param port
     * @param database
     * @param user
     * @param password
     * @return Returns Properties
     */
    protected static Properties toProperties(JDBCVendor vendor, String host, int port, String database, String user, String password) {
	Properties properties;

	properties = new Properties();

	properties.put(JDBCProperties.KEY_URL, vendor.getUrl(host, port, database));
	properties.put(JDBCProperties.KEY_DRIVER, vendor.getDriver());
	properties.put(JDBCProperties.KEY_USER, user);
	properties.put(JDBCProperties.KEY_PASSWORD, password);
	properties.put(JDBCProperties.KEY_FACTORY_CLASS, DataSourceFactory.JDBC_MANAGER);

	return properties;
    }

    /**
     * @param connection
     * @return Returns if connection correspond to Oracle
     * @throws SQLException
     */
    public static boolean isSQLServer(Connection connection)
	throws SQLException {
	return JDBCUtil.getDatabaseName(connection).toLowerCase().indexOf("microsoft") > -1;
    }

    /**
     * @param connection
     * @return Returns if connection correspond to Oracle
     * @throws SQLException
     */
    public static boolean isMySQL(Connection connection)
	throws SQLException {
	return JDBCUtil.getDatabaseName(connection).toLowerCase().indexOf("mysql") > -1;
    }

    /**
     * @param connection
     * @return Returns if connection correspond to Oracle
     * @throws SQLException
     */
    public static boolean isPostgre(Connection connection)
	throws SQLException {
	return JDBCUtil.getDatabaseName(connection).toLowerCase().indexOf("postgre") > -1;
    }

    /**
     * @param connection
     * @return Returns if connection correspond to Oracle
     * @throws SQLException
     */
    public static boolean isOracle(Connection connection)
	throws SQLException {
	return JDBCUtil.getDatabaseName(connection).toLowerCase().indexOf("oracle") > -1;
    }

    /**
     * @param connection
     * @return Returns if connection correspond to DB2
     * @throws SQLException
     */
    public static boolean isDB2(Connection connection)
	throws SQLException {
	return JDBCUtil.getDatabaseName(connection).toLowerCase().indexOf("db2") > -1;
    }

    /**
     * @param connection
     * @return Returns Database Name
     * @throws SQLException
     */
    protected static String getDatabaseName(Connection connection)
	throws SQLException {
	return connection.getMetaData().getDatabaseProductName();
    }
}
