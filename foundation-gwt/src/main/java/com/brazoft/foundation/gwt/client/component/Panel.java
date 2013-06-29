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

package com.brazoft.foundation.gwt.client.component;

import java.util.Iterator;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class Panel
    extends ComplexPanel {

    public Panel(Element element) {
	this.setElement(element);
    }

    @Override
    public void add(Widget add) {
	this.add(add, this.getElement());
    }

    public void insert(Widget add, Widget before) {
	int beforeIndex = this.getWidgetIndex(before);
	this.insert(add, this.getElement(), beforeIndex, true);
    }

    public Iterable<Widget> children() {
	return new Collection(this.iterator());
    }

    class Collection
	implements Iterable<Widget> {

	private Iterator<Widget> iterator;

	public Collection(Iterator<Widget> iterator) {
	    super();
	    this.iterator = iterator;
	}

	@Override
	public Iterator<Widget> iterator() {
	    return this.iterator;
	}
    }

}