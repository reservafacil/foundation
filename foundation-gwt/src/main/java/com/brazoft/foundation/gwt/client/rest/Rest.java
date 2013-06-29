/**
 * Copyright (C) 2009-2012 the original author or authors. See the notice.md file distributed with
 * this work for additional information regarding copyright ownership.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.brazoft.foundation.gwt.client.rest;

import java.util.*;

import org.fusesource.restygwt.client.*;

import com.google.gwt.http.client.URL;

public class Rest {

    private Resource resource;

    public Rest(String uri, Map<String, String> headers) {
	this.resource = new RestResource(uri, headers);
    }

    public Rest(String uri, String query, Map<String, String> headers) {
	this.resource = new RestResource(uri, query, headers);
    }

    public Rest(String uri, String query) {
	this.resource = new RestResource(uri, query);
    }

    public Rest(String uri) {
	this.resource = new RestResource(uri);
    }

    public Rest(String uri, PathBuilder builder) {
	this.resource = new RestResource(uri + builder.toString());
    }

    public RestMethod delete() {
	return new RestMethod(this.resource.delete());
    }

    public RestMethod get() {
	return new RestMethod(this.resource.get());
    }

    public RestMethod head() {
	return new RestMethod(this.resource.head());
    }

    public RestMethod options() {
	return new RestMethod(this.resource.options());
    }

    public RestMethod post() {
	return new RestMethod(this.resource.post());
    }

    public RestMethod put() {
	return new RestMethod(this.resource.put());
    }

    public Rest addHeaderParam(String key, String value) {
	this.resource.getHeaders().put(key, value);
	return this;
    }

    class RestResource
	extends Resource {

	RestResource(String uri, Map<String, String> headers) {
	    super(uri, headers);
	    // TODO Auto-generated constructor stub
	}

	RestResource(String uri, String query, Map<String, String> headers) {
	    super(uri, query, headers);
	    // TODO Auto-generated constructor stub
	}

	RestResource(String uri, String query) {
	    super(uri, query);
	    // TODO Auto-generated constructor stub
	}

	RestResource(String uri) {
	    super(uri);
	    // TODO Auto-generated constructor stub
	}

	@Override
	protected Map<String, String> defaultHeaders() {
	    return new HashMap<String, String>();
	}
    }

    public static class PathBuilder {

	private StringBuffer target;

	private StringBuffer query = new StringBuffer();

	private PathBuilder(String uri) {
	    this.target = new StringBuffer(this.parse(uri));
	}

	public static PathBuilder get(String uri) {
	    return new PathBuilder(uri);
	}

	public PathBuilder append(Boolean value) {
	    return this.add(value);
	}

	public PathBuilder append(Number value) {
	    return this.add(value);
	}

	public PathBuilder append(String value) {
	    return this.add(value);
	}

	public PathBuilder append(Object value) {
	    return this.add(value);
	}

	public PathBuilder addQueryParam(String key, String value) {
	    if (this.query.length() == 0) {
		this.query.append("?");
	    } else {
		this.query.append("&");
	    }

	    key = URL.encodeQueryString(key);
	    value = URL.encodeQueryString(value);

	    this.query.append(key).append("=").append(value);

	    return this;
	}

	PathBuilder add(Object value) {
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
}