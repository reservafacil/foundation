/**
 * Copyright (C) 2009-2012 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brazoft.foundation.gwt.client.rest;

import java.util.Map;

import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestMethod;

public class Rest extends Resource
{
	public Rest(String uri, Map<String, String> headers)
	{
		super(uri, headers);
	}

	public Rest(String uri, String query, Map<String, String> headers)
	{
		super(uri, query, headers);
	}

	public Rest(String uri, String query)
	{
		super(uri, query);
	}

	public Rest(String uri)
	{
		super(uri);
	}
	
	public Rest(String uri, PathBuilder builder)
	{
		super(uri + builder.toString());
	}
	
	@Override
	public RestMethod delete()
	{
		return new RestMethod(super.delete());
	}
	
	@Override
	public RestMethod get()
	{
		return new RestMethod(super.get());
	}
	
	@Override
	public RestMethod head()
	{
		return new RestMethod(super.head());
	}
	
	@Override
	public RestMethod options()
	{
		return new RestMethod(super.options());
	}
	
	@Override
	public RestMethod post()
	{
		return new RestMethod(super.post());
	}
	
	@Override
	public RestMethod put()
	{
		return new RestMethod(super.put());
	}

	@Override
	public Rest resolve(String path)
	{
		super.resolve(path);
		return this;
	}

	@Override
	public Rest addQueryParam(String key, String value)
	{
		super.addQueryParam(key, value);
		return this;
	}

	@Override
	public Rest addQueryParams(String key, Iterable<String> values)
	{
		super.addQueryParams(key, values);
		return this;
	}
	
	public static class PathBuilder
	{
		private StringBuffer	target;
		
		private PathBuilder(String uri)
		{
			this.target = new StringBuffer(this.parse(uri));
		}

		public static PathBuilder get(String uri)
		{
			return new PathBuilder(uri);
		}

		public PathBuilder append(Boolean value)
		{
			return this.add(value);
		}

		public PathBuilder append(Number value)
		{
			return this.add(value);
		}

		public PathBuilder append(String value)
		{
			return this.add(value);
		}

		public PathBuilder append(Object value)
		{
			return this.add(value);
		}

		PathBuilder add(Object value)
		{
			this.target.append("/").append(value);
			return this;
		}
		
		@Override
		public String toString()
		{
			return this.target.toString();
		}
		
		String parse(String uri)
		{
			return uri.endsWith("/") ? uri.substring(0, uri.length() - 1) : uri;
		}
	}
}