package com.brazoft.foundation.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLBuilder {

  private StringBuffer target;

  private StringBuffer query = new StringBuffer();

  private String encoding;

  public URLBuilder(String uri) {
    this(uri, "ISO-8859-1");
  }

  public URLBuilder(String uri, String encoding) {
    this.target = new StringBuffer(this.parse(uri));
    this.encoding = encoding;
  }

  public URLBuilder append(Boolean value) {
    return this.add(value);
  }

  public URLBuilder append(Number value) {
    return this.add(value);
  }

  public URLBuilder append(String value) {
    return this.add(value);
  }

  public URLBuilder append(Object value) {
    return this.add(value);
  }

  public URLBuilder addQueryParam(String key, String value) {
    if (this.query.length() == 0) {
      this.query.append("?");
    } else {
      this.query.append("&");
    }

    try {
      key = URLEncoder.encode(key, this.encoding);
      value = URLEncoder.encode(value, this.encoding);
    } catch (UnsupportedEncodingException e) {
    }

    this.query.append(key).append("=").append(value);

    return this;
  }

  URLBuilder add(Object value) {
    this.target.append("/").append(value);
    return this;
  }

  @Override
  public String toString() {
    return this.target.append(this.query).toString();
  }

  String parse(String uri) {
    return uri.endsWith("/") ? uri.substring(0, uri.length() - 1) : uri;
  }
}
