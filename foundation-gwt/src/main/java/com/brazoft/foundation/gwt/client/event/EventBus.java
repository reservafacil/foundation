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

package com.brazoft.foundation.gwt.client.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brazoft.foundation.gwt.client.event.api.EventHandler;

public class EventBus
{
	private Map<String, List<EventHandler<Object>>> events;
	
	public EventBus()
	{
		this.events = new HashMap<String, List<EventHandler<Object>>>();
	}
	
	public EventBus add(String type, EventHandler<Object> event)
	{
		if(!this.events.containsKey(type))
		{
			this.events.put(type, new ArrayList<EventHandler<Object>>());
		}
		
		this.events.get(type).add(event);
		
		return this;
	}
	
	public Iterable<String> types()
	{
		return this.events.keySet();
	}
	
	public EventHandler<Object> get(String type)
	{
		return this.events.get(type).get(0);
	}
	
	public List<EventHandler<Object>> list(String type)
	{
		return this.events.get(type);
	}
	
	public EventBus fire(String type, Event<Object> e)
	{
		if(!this.events.containsKey(type))
		{
			return this;
		}
		
		for(EventHandler<Object> handler : this.list(type))
		{
			handler.onEvent(e);
		}
		
		return this;
	}
}