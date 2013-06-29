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
import com.brazoft.foundation.gwt.client.event.Events;
import com.brazoft.foundation.gwt.client.ui.Grid.GridOptions;
import com.brazoft.foundation.gwt.client.ui.Layout.LayoutOptions;
import com.brazoft.foundation.gwt.client.ui.api.*;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.Widget;

public final class LayoutRow
    extends Bootstrap<LayoutRow> {

    private LayoutRow() {
	super(ElementResolver.div());
    }

    LayoutRow(GridOptions option) {
	this();
	this.setStyleName(option.rowClass());
    }

    public LayoutRow(LayoutOptions option) {
	this();
	this.setStyleName(option.rowClass());
    }

    public LayoutCell cell() {
	LayoutCell cell = new LayoutCell();

	this.add(cell);

	return cell;
    }

    public class LayoutCell
	extends Bootstrap<LayoutCell>
	implements UICell<LayoutCell> {

	private int span;

	private int offset;

	public LayoutCell() {
	    this(1);
	}

	public LayoutCell(int span) {
	    super(ElementResolver.div());
	    this.span(span);
	}

	public LayoutCell span(int span) {
	    this.removeClassName("span" + this.span);
	    this.span = span;

	    return this.className("span" + span);
	}

	public LayoutCell offset(int offset) {
	    this.removeClassName("offset" + this.offset);
	    this.offset = offset;

	    return this.className("offset" + offset);
	}

	public LayoutCell add(Widget add) {
	    return super.add(add);
	}

	@Override
	public LayoutCell detachChildren() {
	    return super.detachChildren();
	}

	@Override
	public LayoutCell text(String text) {
	    return Component.Util.setHTML(this, text);
	}

	@Override
	public String getText() {
	    return Component.Util.getHTML(this);
	}

	@Override
	public LayoutCell onClick(ClickHandler handler) {
	    return Events.on(this, handler);
	}

	@Override
	public LayoutCell onDoubleClick(DoubleClickHandler handler) {
	    return Events.on(this, handler);
	}

	@Override
	public LayoutCell onMouseDown(MouseDownHandler handler) {
	    return Events.on(this, handler);
	}

	@Override
	public LayoutCell onMouseMove(MouseMoveHandler handler) {
	    return Events.on(this, handler);
	}

	@Override
	public LayoutCell onMouseOut(MouseOutHandler handler) {
	    return Events.on(this, handler);
	}

	@Override
	public LayoutCell onMouseOver(MouseOverHandler handler) {
	    return Events.on(this, handler);
	}

	@Override
	public LayoutCell onMouseUp(MouseUpHandler handler) {
	    return Events.on(this, handler);
	}

	@Override
	public LayoutCell onMouseWheel(MouseWheelHandler handler) {
	    return Events.on(this, handler);
	}

	@Override
	public LayoutCell colspan(int colspan) {
	    GWT.log("LayoutRow does not support colspan(). Using span() instead.");
	    this.span(this.span * colspan);
	    return this;
	}

	@Override
	public LayoutCell rowspan(int rowspan) {
	    GWT.log("LayoutRow does not support rowspan(). Using offset() instead.");
	    this.offset(this.span * rowspan);
	    return this;
	}

	@Override
	public LayoutCell icon(Icon icon) {
	    Widgets.setIcon(this, icon);
	    return this;
	}

	@Override
	public LayoutCell verticalAlign(VerticalAlignment vAlign) {
	    if (VerticalAlignment.MIDDLE.equals(vAlign)) {
		this.style().verticalAlign(50, Unit.PCT);
	    } else if (VerticalAlignment.BOTTOM.equals(vAlign)) {
		this.style().verticalAlign(100, Unit.PCT);
	    }

	    return this;
	}
    }
}
