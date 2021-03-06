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

package com.brazoft.foundation.gwt.client.event;

import java.util.*;

import com.brazoft.foundation.gwt.client.event.api.*;

@SuppressWarnings("unchecked")
public class EventBus {

	private final Map<EventType, List<EventHandler<?>>> events;

	public EventBus() {
		this.events = new HashMap<EventType, List<EventHandler<?>>>();
	}

	public <T> EventBus add(EventType type, EventHandler<T> handler) {
		if (!this.events.containsKey(type)) {
			this.events.put(type, new ArrayList<EventHandler<?>>());
		}

		this.events.get(type).add(handler);

		return this;
	}

	public <T> EventBus fire(Event<T> e) {
		if (!this.events.containsKey(e.type())) {
			return this;
		}

		List<EventHandler<?>> handlers = this.events.get(e.type());

		for (EventHandler<?> handler : handlers) {
			((EventHandler<T>)handler).fire(e);
		}

		return this;
	}

	public <T> EventBus remove(EventHandler<T> handler) {
		handler.canceled();
		return this;
	}

	public EventBus remove(EventType type) {

		List<EventHandler<?>> handlers = this.events.get(type);

		for (EventHandler<?> handler : handlers) {
			handler.canceled();
		}

		return this;
	}

	public Iterable<EventType> types() {
		return this.events.keySet();
	}
}