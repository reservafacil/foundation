package com.brazoft.foundation.util;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public enum JDBCVendor {
  /**
	 * 
	 */
  HSQLDB,

  /**
	 * 
	 */
  MSSQLSERVER,

  /**
	 * 
	 */
  ORACLE,

  /**
	 * 
	 */
  MYSQL,

  /**
	 * 
	 */
  POSTGRE,

  /**
	 * 
	 */
  DB2;

  /**
   * @param id
   * @return Returns JDBCVendor
   */
  public static JDBCVendor fromId(int id) {
    for (JDBCVendor vendor : JDBCVendor.values()) {
      if (id == vendor.getId()) {
        return vendor;
      }
    }

    return null;
  }

  /**
   * @param driver
   * @return Returns JDBCVendor from driver name
   */
  public static JDBCVendor fromDriver(String driver) {
    for (JDBCVendor vendor : JDBCVendor.values()) {
      if (vendor.getDriver().equals(driver)) {
        return vendor;
      }
    }

    return null;
  }

  /**
   * @return Returns driver
   */
  public String getDriver() {
    switch (this) {
      case HSQLDB:
        return "org.hsqldb.jdbcDriver";
      case MSSQLSERVER:
        return "net.sourceforge.jtds.jdbc.Driver";
      case ORACLE:
        return "oracle.jdbc.driver.OracleDriver";
      case MYSQL:
        return "org.gjt.mm.mysql.Driver";
      case POSTGRE:
        return "org.postgresql.Driver";
      case DB2:
        return "COM.ibm.db2.jdbc.app.DB2Driver";
    }

    return null;
  }

  /**
   * @return Returns default port number
   */
  public int getDefaultPort() {
    switch (this) {
      case HSQLDB:
        return 9001;
      case MSSQLSERVER:
        return 1433;
      case ORACLE:
        return 1521;
      case MYSQL:
        return 3306;
      case POSTGRE:
        return 5432;
      case DB2:
        return 50000;
    }

    return -1;
  }

  /**
   * @return Returns internal id
   */
  public int getId() {
    switch (this) {
      case MSSQLSERVER:
        return 1;
      case ORACLE:
        return 2;
      case MYSQL:
        return 3;
      case POSTGRE:
        return 4;
      case DB2:
        return 5;
      case HSQLDB:
        return 6;
    }

    return -1;
  }

  /**
   * @return Returns Vendor name
   */
  public String getName() {
    switch (this) {
      case HSQLDB:
        return "HyperSQL";
      case MSSQLSERVER:
        return "Microsoft SQL Server";
      case ORACLE:
        return "Oracle";
      case MYSQL:
        return "MySQL";
      case POSTGRE:
        return "Postgre SQL";
      case DB2:
        return "IBM DB2";
    }

    return null;
  }

  /**
   * @param host
   * @param database
   * @return Returns url pattern using default port
   */
  public String getUrl(String host, String database) {
    return this.getUrl(host, this.getDefaultPort(), database);
  }

  /**
   * @param host
   * @param port
   * @param database
   * @return Returns url pattern
   */
  public String getUrl(String host, int port, String database) {
    String url;

    url = null;

    switch (this) {
      case HSQLDB:
        url = "jdbc:hsqldb:hsql://<host>:<port>/<database>";
        break;
      case MSSQLSERVER:
        url = "jdbc:jtds:sqlserver://<host>:<port>/<database>";
        break;
      case ORACLE:
        url = "jdbc:oracle:thin:@<host>:<port>:<database>";
        break;
      case MYSQL:
        url = "jdbc:mysql://<host>:<port>/<database>";
        break;
      case POSTGRE:
        url = "jdbc:postgresql://<host>:<port>/<database>";
        break;
      case DB2:
        url = "jdbc:db2:<databse>";
        break;
    }

    url = url.replace("<host>", host);
    url = url.replace("<port>", Converter.toString(port));

    return url.replace("<database>", database);
  }

  /**
   * @return Returns if vendor support identity
   */
  public boolean isIdentitySupported() {
    switch (this) {
      case HSQLDB:
        return true;
      case MSSQLSERVER:
        return true;
      case MYSQL:
        return true;
    }

    return false;
  }

  /**
   * @return Returns if vendor support sequences
   */
  public boolean isSequenceSupported() {
    switch (this) {
      case HSQLDB:
        return true;
      case DB2:
        return true;
      case ORACLE:
        return true;
      case POSTGRE:
        return true;
    }

    return false;
  }
}