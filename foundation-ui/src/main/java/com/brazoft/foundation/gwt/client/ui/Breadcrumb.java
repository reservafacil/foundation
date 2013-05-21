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

package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.brazoft.foundation.gwt.client.event.api.HasClickHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasMouseHandlers;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.user.client.ui.Widget;

public final class Breadcrumb
    extends Bootstrap<Breadcrumb> {

    private String separator;

    public Breadcrumb() {
	this("/");
    }

    public Breadcrumb(String separator) {
	super(ElementResolver.ul());
	this.className("breadcrumb");
	this.separator = separator;
    }

    public BreadcrumbItem item(String label) {
	BreadcrumbItem item = new BreadcrumbItem().text(label);

	if (this.hasChildren()) {
	    ((BreadcrumbItem)this.getChild(this.childrenCount() - 1)).activate();
	    item.insert(this.divider());
	}

	this.add(item);

	return item;
    }

    HTML<SpanElement> divider() {
	return HTML.asSpan().className("divider").text(this.separator);
    }

    public static class BreadcrumbItem
	extends Bootstrap<BreadcrumbItem>
	implements HasText<BreadcrumbItem>, HasClickHandlers<BreadcrumbItem>, HasMouseHandlers<BreadcrumbItem>,
	HasFocusHandlers<BreadcrumbItem> {

	private HTML<AnchorElement> link = HTML.asAnchor("#");

	public BreadcrumbItem() {
	    super(ElementResolver.li());
	    this.add(this.link);
	    Widgets.muted(this.link);
	}

	@Override
	public BreadcrumbItem onBlur(BlurHandler handler) {
	    this.link.onBlur(handler);
	    return this;
	}

	@Override
	public BreadcrumbItem onFocus(FocusHandler handler) {
	    this.link.onFocus(handler);
	    return this;
	}

	@Override
	public BreadcrumbItem onMouseDown(MouseDownHandler handler) {
	    this.link.onMouseDown(handler);
	    return this;
	}

	@Override
	public BreadcrumbItem onMouseMove(MouseMoveHandler handler) {
	    this.link.onMouseMove(handler);
	    return this;
	}

	@Override
	public BreadcrumbItem onMouseOut(MouseOutHandler handler) {
	    this.link.onMouseOut(handler);
	    return this;
	}

	@Override
	public BreadcrumbItem onMouseOver(MouseOverHandler handler) {
	    this.link.onMouseOver(handler);
	    return this;
	}

	@Override
	public BreadcrumbItem onMouseUp(MouseUpHandler handler) {
	    this.link.onMouseUp(handler);
	    return this;
	}

	@Override
	public BreadcrumbItem onMouseWheel(MouseWheelHandler handler) {
	    this.link.onMouseWheel(handler);
	    return this;
	}

	@Override
	public BreadcrumbItem onClick(ClickHandler handler) {
	    this.link.onClick(handler);
	    return this;
	}

	@Override
	public BreadcrumbItem onDoubleClick(DoubleClickHandler handler) {
	    this.link.onDoubleClick(handler);
	    return this;
	}

	@Override
	public BreadcrumbItem text(String text) {
	    this.link.text(text);
	    return this;
	}

	@Override
	public String getText() {
	    return this.link.getText();
	}

	protected BreadcrumbItem insert(Widget child) {
	    return super.insert(child, this.link);
	}

	@Override
	protected BreadcrumbItem add(Widget add) {
	    return super.add(add);
	}

	BreadcrumbItem activate() {
	    this.link.element().setHref("#");
	    this.link.removeClassName("muted");

	    return this;
	}
    }
}
