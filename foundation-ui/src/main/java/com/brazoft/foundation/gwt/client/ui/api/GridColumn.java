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

package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.ui.Icon;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Row.Cell;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.jso.JSObject;

@SuppressWarnings("unchecked")
public abstract class GridColumn<G extends GridColumn<G, J>, J extends JSObject> {

	private Cell              headerCell;

	private HTML<SpanElement> label = HTML.asSpan();

	private String            name;

	private boolean           sortable;

	private double            width;

	private Unit              widthUnit;

	protected Cell headerCell() {
		return this.headerCell;
	}

	public boolean isFilterable() {
		return false;
	}

	public G width(double width, Unit unit) {
		this.width = width;
		this.widthUnit = unit;
		return (G)this;
	}

	public G sort(JsArray<J> rows) {
		return (G)this;
	}

	public G unsort() {
		return (G)this;
	}

	public G headerCell(Cell cell) {
		this.headerCell = cell;
		this.headerCell.add(this.label);

		if (this.sortable) {
			this.headerCell.className("sortable");
		}

		if (this.width > 0) {
			this.headerCell.style().width(this.width, this.widthUnit);
		}

		return (G)this;
	}

	public G sortable() {
		this.sortable = true;
		return (G)this;
	}

	public G onClick(ClickHandler handler) {
		this.headerCell.onClick(handler);
		return (G)this;
	}

	public G icon(Icon icon) {
		this.headerCell.icon(icon);
		return (G)this;
	}

	public G text(String text) {
		this.label.text(text);
		return (G)this;
	}

	public String getName() {
		return name;
	}

	public G name(String name) {
		this.name = name;

		return (G)this;
	}

	public G label(String label) {
		this.label.text(label);
		return (G)this;
	}
	
	protected String getLabel() {
		return this.label.getText();
	}

	public String toString(J object) {
		return object.toString();
	}

	public abstract G render(int rowIndex, Cell cell, J object);
}