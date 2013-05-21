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

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.brazoft.foundation.gwt.client.component.api.ResponsiveComponent;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.event.api.HasChangeHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasKeyHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasMouseHandlers;
import com.brazoft.foundation.gwt.client.ui.*;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public abstract class DecoratedInput<D extends DecoratedInput<D, V>, V>
    extends Bootstrap<D>
    implements UIInput<D, V>, HasKeyHandlers<D>, HasFocusHandlers<D>, HasMouseHandlers<D>, HasChangeHandlers<D>, ResponsiveComponent<D> {

    private Input<?, String> input;

    public enum Decoration {
	APPENDED, PREPENDED;

	String className() {
	    if (this.equals(APPENDED)) {
		return "input-append";
	    }

	    return "input-prepend";
	}
    }

    public DecoratedInput(Input<?, String> input) {
	super(ElementResolver.div());
	this.input = input;
	this.add(this.input);
    }

    public D onChange(ChangeHandler handler) {
	this.input.onChange(handler);
	return (D)this;
    }

    @Override
    public D onMouseDown(MouseDownHandler handler) {
	this.input.onMouseDown(handler);
	return (D)this;
    }

    @Override
    public D onMouseMove(MouseMoveHandler handler) {
	this.input.onMouseMove(handler);
	return (D)this;
    }

    @Override
    public D onMouseOut(MouseOutHandler handler) {
	this.input.onMouseOut(handler);
	return (D)this;
    }

    @Override
    public D onMouseOver(MouseOverHandler handler) {
	this.input.onMouseOver(handler);
	return (D)this;
    }

    @Override
    public D onMouseUp(MouseUpHandler handler) {
	this.input.onMouseUp(handler);
	return (D)this;
    }

    @Override
    public D onMouseWheel(MouseWheelHandler handler) {
	this.input.onMouseWheel(handler);
	return (D)this;
    }

    @Override
    public D onFocus(FocusHandler handler) {
	this.input.onFocus(handler);
	return (D)this;
    }

    @Override
    public D onBlur(BlurHandler handler) {
	this.input.onBlur(handler);
	return (D)this;
    }

    @Override
    public D onKeyPress(KeyPressHandler handler) {
	this.input.onKeyPress(handler);
	return (D)this;
    }

    @Override
    public D onKeyDown(KeyDownHandler handler) {
	this.input.onKeyDown(handler);
	return (D)this;
    }

    @Override
    public D onKeyUp(KeyUpHandler handler) {
	this.input.onKeyUp(handler);
	return (D)this;
    }

    public D blur() {
	this.input.blur();

	return (D)this;
    }

    public D focus() {
	this.input.focus();

	return (D)this;
    }

    public D span(int span) {
	this.input.span(span);
	return (D)this;
    }

    public D placeholder(String placeholder) {
	this.input.placeholder(placeholder);

	return (D)this;
    }

    public D size(Size size) {
	switch (size) {
	    case LARGE:
		this.input.className("input-large");
		return (D)this;
	    case MINI:
		this.input.className("input-mini");
		return (D)this;
	    case SMALL:
		this.input.className("input-small");
		return (D)this;
	    default:
		break;
	}

	this.input.className("input-medium");
	return (D)this;
    }

    @Override
    public boolean isReadOnly() {
	return this.input.isReadOnly();
    }

    @Override
    public D readonly() {
	this.input.readonly();
	return (D)this;
    }

    @Override
    public boolean isEditable() {
	return this.input.isEditable();
    }

    @Override
    public D editable() {
	this.input.editable();
	return (D)this;
    }

    @Override
    public boolean isNullable() {
	return this.input.isNullable();
    }

    @Override
    public D nullable() {
	this.input.nullable();
	return (D)this;
    }

    @Override
    public boolean isRequired() {
	return this.input.isRequired();
    }

    @Override
    public D required() {
	this.input.required();
	return (D)this;
    }

    public D responsiveTo(final Widget container) {
	return Component.Util.responsiveBehavior(this, container);
    }

    public D adaptSize(Widget container) {
	double addOnWidth = 0;
	double containerWidth = Component.Util.innerWidth(container);

	for (Widget child : this.getChildren()) {
	    if (!child.equals(this.input)) {
		addOnWidth += child.getOffsetWidth();
	    }
	}

	double inputWidth = containerWidth
	        - addOnWidth
	        - Component.Util.computeInnerLeft(container)
	        - Component.Util.computeInnerRight(this.input)
	        - Component.Util.computeInnerLeft(this.input);

	this.input.style().width(inputWidth, Unit.PX);

	return (D)this;
    }
    
    public D prepend(String text) {
	return this.add(text, Decoration.PREPENDED);
    }

    public D append(String text) {
	return this.add(text, Decoration.APPENDED);
    }

    public D prepend(Icon icon) {
	return this.add(icon, Decoration.PREPENDED);
    }

    public D append(Icon icon) {
	return this.add(icon, Decoration.APPENDED);
    }

    public D prepend(Button button) {
	return this.add(button, Decoration.PREPENDED);
    }

    public D append(Button button) {
	return this.add(button, Decoration.APPENDED);
    }

    public D prepend(SplitButton button) {
	return this.add(button, Decoration.PREPENDED);
    }

    public D append(SplitButton button) {
	return this.add(button, Decoration.APPENDED);
    }

    public Input<?, String> input() {
	return this.input;
    }

    @Override
    protected D add(Widget add) {
	return super.add(add);
    }

    protected D add(Icon icon, Decoration decoration) {
	HTML<SpanElement> addOn = HTML.asSpan().className("add-on");
	Widgets.setIcon(addOn, icon);

	return this.add(addOn, decoration);
    }

    protected D add(String text, Decoration decoration) {
	HTML<SpanElement> addOn = HTML.asSpan().className("add-on").text(text);

	return this.add(addOn, decoration);
    }

    protected D add(Widget addOn, Decoration decoration) {
	this.className(decoration.className());

	if (decoration.equals(Decoration.PREPENDED)) {
	    return this.insert(addOn, this.input);
	}

	return this.add(addOn);
    }
}