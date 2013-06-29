package com.brazoft.foundation.util;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class ConnectionProperties {

  private JDBCVendor vendor;

  private String host;

  private int port;

  private String database;

  private String driver;

  private String url;

  private String user;

  private String password;

  private String jndi;

  /**
   * @param vendor
   * @param jndi
   */
  public ConnectionProperties(JDBCVendor vendor, String jndi) {
    this.vendor = vendor;
    this.jndi = jndi;
  }

  /**
   * @param vendor
   * @param host
   * @param database
   */
  public ConnectionProperties(JDBCVendor vendor, String host, String database) {
    this(vendor, host, vendor.getDefaultPort(), database);
  }

  /**
   * @param vendor
   * @param host
   * @param port
   * @param database
   */
  public ConnectionProperties(JDBCVendor vendor, String host, int port, String database) {
    this.vendor = vendor;
    this.host = host;
    this.port = port;
    this.database = database;
    this.driver = this.vendor.getDriver();
    this.url = this.vendor.getUrl(this.host, this.port, this.database);
  }

  /**
   * @param driver
   * @param url
   */
  public ConnectionProperties(String driver, String url) {
    this.driver = driver;
    this.url = url;
    this.vendor = JDBCVendor.fromDriver(driver);
  }

  /**
   * @return Returns JDBCVendor
   */
  public JDBCVendor getVendor() {
    return vendor;
  }

  /**
   * @return the host
   */
  public String getHost() {
    return host;
  }

  /**
   * @return the port
   */
  public int getPort() {
    return port;
  }

  /**
   * @return the database name
   */
  public String getDatabase() {
    return database;
  }

  /**
   * @return the driver
   */
  public String getDriver() {
    return this.driver;
  }

  /**
   * @return Returns the url
   */
  public String getUrl() {
    return this.url;
  }

  /**
   * @return the user
   */
  public String getUser() {
    return user;
  }

  /**
   * @param user the user to set
   */
  public void setUser(String user) {
    this.user = user;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the jndi
   */
  public String getJndi() {
    return jndi;
  }

  /**
   * @param jndi the jndi to set
   */
  public void setJndi(String jndi) {
    this.jndi = jndi;
  }
}