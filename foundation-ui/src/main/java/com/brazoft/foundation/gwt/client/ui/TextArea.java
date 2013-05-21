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

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.event.Events;
import com.brazoft.foundation.gwt.client.event.api.HasChangeHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasKeyHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasMouseHandlers;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.google.gwt.dom.client.TextAreaElement;
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

public final class TextArea
    extends Bootstrap<TextArea>
    implements UIInput<TextArea, String>, HasFocusHandlers<TextArea>, HasMouseHandlers<TextArea>, HasKeyHandlers<TextArea>,
    HasChangeHandlers<TextArea> {

    public TextArea() {
	super(ElementResolver.textarea());
    }

    public TextArea onChange(ChangeHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public TextArea onKeyPress(KeyPressHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public TextArea onKeyDown(KeyDownHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public TextArea onKeyUp(KeyUpHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public TextArea onMouseDown(MouseDownHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public TextArea onMouseMove(MouseMoveHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public TextArea onMouseOut(MouseOutHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public TextArea onMouseOver(MouseOverHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public TextArea onMouseUp(MouseUpHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public TextArea onMouseWheel(MouseWheelHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public TextArea onFocus(FocusHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public TextArea onBlur(BlurHandler handler) {
	return Events.on(this, handler);
    }

    public TextArea cols(int cols) {
	this.element().setCols(cols);
	return this;
    }

    public TextArea rows(int rows) {
	this.element().setRows(rows);
	return this;
    }

    @Override
    public TextArea clear() {
	return this.value("");
    }

    @Override
    public TextArea value(String value) {
	this.element().setValue(value);
	return this;
    }

    @Override
    public String getValue() {
	return this.element().getValue();
    }

    public TextArea placeholder(String placeholder) {
	return this.attribute("placeholder", placeholder);
    }

    @Override
    public boolean isReadOnly() {
	return this.element().isReadOnly();
    }

    @Override
    public TextArea readonly() {
	this.element().setReadOnly(true);
	return this;
    }

    @Override
    public boolean isEditable() {
	return !this.isReadOnly();
    }

    @Override
    public TextArea editable() {
	this.element().setReadOnly(false);
	return this;
    }

    @Override
    public boolean isNullable() {
	return !this.isRequired();
    }

    @Override
    public TextArea nullable() {
	return this.attribute("required", null);
    }

    @Override
    public boolean isRequired() {
	return this.getAttribute("required") != null;
    }

    @Override
    public TextArea required() {
	return this.attribute("required", "");
    }

    public TextArea block() {
	return this.className("input-block-level");
    }

    TextAreaElement element() {
	return this.getElement().cast();
    }
}