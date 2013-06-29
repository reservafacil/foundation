package com.brazoft.foundation.util;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class SMTPProperties {

  private boolean authentication;

  private String from;

  private String host;

  private int port;

  private String username;

  private String password;

  /**
	 * 
	 */
  public SMTPProperties() {
    this.port = 25;
  }

  /**
   * @return the authentication
   */
  public boolean isAuthentication() {
    return authentication;
  }

  /**
   * @param authentication the authentication to set
   */
  public void setAuthentication(boolean authentication) {
    this.authentication = authentication;
  }

  /**
   * @return the from
   */
  public String getFrom() {
    return from;
  }

  /**
   * @param from the from to set
   */
  public void setFrom(String from) {
    this.from = from;
  }

  /**
   * @return the host
   */
  public String getHost() {
    return host;
  }

  /**
   * @param host the host to set
   */
  public void setHost(String host) {
    this.host = host;
  }

  /**
   * @return the port
   */
  public int getPort() {
    return port;
  }

  /**
   * @param port the port to set
   */
  public void setPort(int port) {
    this.port = port;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
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
}