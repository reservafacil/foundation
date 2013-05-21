package com.brazoft.foundation.jdbc;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import com.brazoft.foundation.util.ConnectionProperties;
import com.brazoft.foundation.util.Converter;
import com.brazoft.foundation.util.JDBCVendor;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class JDBCProperties {

    private ConnectionProperties properties;

    private String               implementationClass;

    private int                  maxActive;

    private int                  maxWait;

    private int                  maxIdle;

    /**
     * @param properties
     * @param implementationClass
     */
    public JDBCProperties(ConnectionProperties properties, String implementationClass) {
	super();
	this.properties = properties;
	this.implementationClass = implementationClass;
    }

    /**
     * @param name
     * @return ConnectionString from bundle
     */
    public static JDBCProperties newInstance(String name) {
	ResourceBundle bundle;
	Properties properties;
	Enumeration<String> keys;
	String key;

	bundle = ResourceBundle.getBundle(name);
	properties = new Properties();
	keys = bundle.getKeys();

	while (keys.hasMoreElements()) {
	    key = keys.nextElement();
	    properties.put(key, bundle.getString(key));
	}

	return JDBCProperties.newInstance(properties);
    }

    /**
     * @param properties
     * @return Returns ConnectionString instance
     */
    public static JDBCProperties newInstance(Properties properties) {
	JDBCProperties instance;
	ConnectionProperties connectionProperties;
	String className;

	className = properties.getProperty(JDBCProperties.KEY_DRIVER);
	className = JDBCProperties.trim(className);

	connectionProperties = new ConnectionProperties(className, properties.getProperty(JDBCProperties.KEY_URL));
	connectionProperties.setPassword(properties.getProperty(JDBCProperties.KEY_PASSWORD));
	connectionProperties.setUser(properties.getProperty(JDBCProperties.KEY_USER));
	connectionProperties.setJndi(properties.getProperty(JDBCProperties.KEY_JNDI));

	className = properties.getProperty(JDBCProperties.KEY_FACTORY_CLASS);
	className = JDBCProperties.trim(className);

	instance = new JDBCProperties(connectionProperties, className);
	instance.setMaxActive(Converter.toint(properties.getProperty(JDBCProperties.KEY_POOL_MAXACTIVE, "5")));
	instance.setMaxIdle(Converter.toint(properties.getProperty(JDBCProperties.KEY_POOL_MAXIDLE, "2")));
	instance.setMaxWait(Converter.toint(properties.getProperty(JDBCProperties.KEY_POOL_MAXWAIT, "2")));

	return instance;
    }

    /**
     * @return Returns properties
     */
    public Properties toProperties() {
	Properties properties;

	properties = new Properties();
	properties.put("user", this.properties.getUser());
	properties.put("password", this.properties.getPassword());

	if (this.isOracle()) {
	    properties.put("SetBigStringTryClob", "true");
	}

	return properties;
    }

    /**
     * @return Returns if is oracle database
     */
    public boolean isOracle() {
	return this.properties.getVendor().equals(JDBCVendor.ORACLE);
    }

    /**
     * @return Returns the implementationClass.
     */
    public String getImplementationClass() {
	return this.implementationClass;
    }

    /**
     * @return the maxActive
     */
    public int getMaxActive() {
	return maxActive;
    }

    /**
     * @param maxActive
     *            the maxActive to set
     */
    public void setMaxActive(int maxActive) {
	this.maxActive = maxActive;
    }

    /**
     * @return the maxWait
     */
    public int getMaxWait() {
	return maxWait;
    }

    /**
     * @param maxWait
     *            the maxWait to set
     */
    public void setMaxWait(int maxWait) {
	this.maxWait = maxWait;
    }

    /**
     * @return the maxIdle
     */
    public int getMaxIdle() {
	return maxIdle;
    }

    /**
     * @param maxIdle
     *            the maxIdle to set
     */
    public void setMaxIdle(int maxIdle) {
	this.maxIdle = maxIdle;
    }

    /**
     * @return Returns ConnectionProperties
     */
    public ConnectionProperties getProperties() {
	return properties;
    }

    private static String trim(String value) {
	if (value != null) {
	    return value.trim();
	}

	return value;
    }

    /**
	 * 
	 */
    public static final String KEY_FACTORY_CLASS  = "jdbc.impl.class";

    /**
	 * 
	 */
    public static final String KEY_PASSWORD       = "jdbc.pwd";

    /**
	 * 
	 */
    public static final String KEY_USER           = "jdbc.user";

    /**
	 * 
	 */
    public static final String KEY_URL            = "jdbc.url";

    /**
	 * 
	 */
    public static final String KEY_DRIVER         = "jdbc.driver";

    /**
	 * 
	 */
    public static final String KEY_JNDI           = "jdbc.jndi";

    /**
	 * 
	 */
    public static final String KEY_POOL_MAXACTIVE = "jdbc.pool.maxActive";

    /**
	 * 
	 */
    public static final String KEY_POOL_MAXIDLE   = "jdbc.pool.maxIdle";

    /**
	 * 
	 */
    public static final String KEY_POOL_MAXWAIT   = "jdbc.pool.maxWait";
}