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

package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

public final class List
    extends Bootstrap<List> {

	public List(ListOptions option) {
		super(option.resolveElement());
	}

	public List unstyled() {
		return this.className("unstyled");
	}

	public List inline() {
		return this.className("inline");
	}

	public List item(String value) {
		return this.add(new ListItem(value));
	}

	public List items(String... values) {
		for (String value : values) {
			this.item(value);
		}

		return this;
	}

	public List add(List add) {
		return this.add(add);
	}

	static class ListItem
	    extends Widget {

		public ListItem(String text) {
			this.setElement(ElementResolver.li());
			Component.Util.setHTML(this, text);
		}
	}

	public enum ListOptions {
		UNORDERED, ORDERED;

		Element resolveElement() {
			if (this.equals(UNORDERED)) {
				return ElementResolver.ul();
			}

			return ElementResolver.ol();
		}
	}
}