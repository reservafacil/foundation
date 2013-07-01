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

import com.brazoft.foundation.gwt.client.event.api.EventType;
import com.google.gwt.user.client.ui.Widget;

public class Event<T> {

	private Widget    widget;

	private T         data;

	private EventType type;

	public Event(EventType type, Widget widget) {
		this(type, widget, null);
	}

	public Event(EventType type, T data) {
		this(type, null, data);
	}

	public Event(EventType type, Widget widget, T data) {
		super();
		this.type = type;
		this.widget = widget;
		this.data = data;
	}

	public Widget widget() {
		return this.widget;
	}

	public T data() {
		return this.data;
	}

	public EventType type() {
		return this.type;
	}
}