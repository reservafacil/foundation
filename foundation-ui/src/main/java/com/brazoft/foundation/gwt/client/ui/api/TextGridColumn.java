/**
 * Copyright (C) 2009-2012 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.ui.Icon;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Row.Cell;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.jso.JSObject;

@SuppressWarnings("unchecked")
public abstract class TextGridColumn<T extends TextGridColumn<T, J>, J extends JSObject>
    extends GridColumn<T, J> {

    private SortDirection direction = SortDirection.NONE;

    public TextGridColumn() {
	this.sortable();
    }

    @Override
    public boolean isFilterable() {
	return true;
    }

    @Override
    public T unsort() {
	this.headerCell().removeClassName("sorted").icon(Icon.NONE);
	return (T)this;
    }

    @Override
    public T sort(JsArray<J> rows) {
	this.direction = this.direction.reverse();

	this.headerCell().className("sorted").icon(this.direction.icon());

	this.doSort(rows, this.getName(), this.direction.value());

	return (T)this;
    }

    protected abstract void doSort(JsArray<J> rows, String name, int direction);

    enum SortDirection {
	NONE {

	    @Override
	    public int value() {
		return 0;
	    }

	    @Override
	    public SortDirection reverse() {
		return ASC;
	    }

	    @Override
	    public Icon icon() {
		return null;
	    }
	},
	DESC {

	    @Override
	    public int value() {
		return -1;
	    }

	    @Override
	    public SortDirection reverse() {
		return ASC;
	    }

	    @Override
	    public Icon icon() {
		return Icon.CHEVRON_DOWN;
	    }
	},
	ASC {

	    @Override
	    public int value() {
		return 1;
	    }

	    @Override
	    public SortDirection reverse() {
		return DESC;
	    }

	    @Override
	    public Icon icon() {
		return Icon.CHEVRON_UP;
	    }
	};

	public abstract int value();

	public abstract SortDirection reverse();

	public abstract Icon icon();
    }

    public T render(int rowIndex, Cell cell, J object) {
	cell.text(this.toString(object));
	return (T)this;
    }

    public abstract String toString(J object);
}
