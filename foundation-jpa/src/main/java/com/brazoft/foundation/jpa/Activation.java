package com.brazoft.foundation.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.brazoft.foundation.util.ConnectionProperties;
import com.brazoft.foundation.util.ExceptionHandler;
import com.brazoft.foundation.util.JDBCVendor;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class Activation {

    private static Activation instance = new Activation();

    private Configuration     configuration;

    private SessionFactory    factory;

    private List<Class<?>>    entities;

    /**
	 * 
	 */
    private Activation() {
	super();
	this.entities = new ArrayList<Class<?>>();
    }

    /**
     * @return Returns Activation single instance
     */
    public static Activation getInstance() {
	return Activation.instance;
    }

    /**
     * @param classes
     */
    public void addEntities(List<Class<?>> classes) {
	this.entities.addAll(classes);
    }

    /**
     * @param classes
     */
    public void setEntities(List<String> classes) {
	try {
	    for (String name : classes) {
		this.entities.add(Class.forName(name));
	    }
	} catch (Exception e) {
	    ExceptionHandler.handleRuntime(e);
	}
    }

    /**
     * @param properties
     * @return Returns Properties from ConnectionProperties
     */
    public Properties toProperties(ConnectionProperties properties) {
	Properties p;
	JDBCVendor vendor;

	vendor = properties.getVendor();
	p = new Properties();
	p.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
	// p.setProperty(Environment.QUERY_SUBSTITUTIONS,
	// "true 1, false 0");//hibernate.query.substitutions
	// p.setProperty(Environment.SHOW_SQL, "true");

	// C3P0 Connection Pool
	p.setProperty(Environment.C3P0_MIN_SIZE, "5");
	p.setProperty(Environment.C3P0_MAX_SIZE, "20");
	p.setProperty(Environment.C3P0_TIMEOUT, "1800");
	p.setProperty(Environment.C3P0_MAX_STATEMENTS, "50");

	// Connection
	p.setProperty(Environment.DIALECT, this.getDialect(vendor));
	p.setProperty(Environment.DRIVER, vendor.getDriver());
	p.setProperty(Environment.URL, vendor.getUrl(properties.getHost(), properties.getDatabase()));
	p.setProperty(Environment.USER, properties.getUser());
	p.setProperty(Environment.PASS, properties.getPassword());

	return p;
    }

    /**
	 * 
	 */
    public void doActivation() {
	this.dispose();
	this.init();
	this.done();
    }

    /**
     * @param properties
     */
    public void doActivationBy(ConnectionProperties properties) {
	this.dispose();
	this.init();

	this.configuration.setProperty(Environment.DRIVER, properties.getDriver());
	this.configuration.setProperty(Environment.URL, properties.getUrl());
	this.configuration.setProperty(Environment.USER, properties.getUser());
	this.configuration.setProperty(Environment.PASS, properties.getPassword());

	this.configuration.setProperty(Environment.DIALECT, this.getDialect(properties.getVendor()));
	this.configuration.setProperty(Environment.SHOW_SQL, "true");

	this.done();
    }

    /**
	 * 
	 */
    public void install() {
	SchemaExport exporter;

	exporter = new SchemaExport(this.configuration);
	exporter.create(false, true);
    }

    /**
	 * 
	 */
    public void dispose() {
	if (this.factory != null) {
	    this.factory.close();
	}
	this.factory = null;
	this.configuration = null;
    }

    /**
     * @return Returns hibernate Session
     */
    public Session getSession() {
	return this.factory.openSession();
    }

    /**
	 * 
	 */
    protected void configureConnectionPool() {
	this.configuration.setProperty(Environment.C3P0_MIN_SIZE, "5");
	this.configuration.setProperty(Environment.C3P0_MAX_SIZE, "20");
	this.configuration.setProperty(Environment.C3P0_TIMEOUT, "1800");
	this.configuration.setProperty(Environment.C3P0_MAX_STATEMENTS, "50");
    }

    private void init() {
	this.configuration = new Configuration();
	// this.configuration.setProperty(Environment.QUERY_SUBSTITUTIONS, "true 1, false 0");

	for (Class<?> clazz : this.entities) {
	    this.configuration.addAnnotatedClass(clazz);
	}
    }

    /**
	 * 
	 */
    private void done() {
	this.factory = this.configuration.buildSessionFactory();
	// this.configuration = null;
    }

    private String getDialect(JDBCVendor vendor) {
	switch (vendor) {
	    case DB2:
		return "org.hibernate.dialect.DB2Dialect";
	    case HSQLDB:
		return "org.hibernate.dialect.HSQLDialect";
	    case MSSQLSERVER:
		return "org.hibernate.dialect.SQLServerDialect";
	    case MYSQL:
		return "org.hibernate.dialect.MySQLDialect";
	    case ORACLE:
		return "org.hibernate.dialect.OracleDialect";
	    case POSTGRE:
		return "org.hibernate.dialect.PostgreSQLDialect";
	}

	return null;
    }
}