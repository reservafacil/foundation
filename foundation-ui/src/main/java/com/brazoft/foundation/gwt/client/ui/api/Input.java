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

import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.event.Events;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.event.api.HasChangeHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasClickHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.ui.Size;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.impl.TextBoxImpl;

@SuppressWarnings("unchecked")
public abstract class Input<I extends Input<I, V>, V>
    extends Bootstrap<I>
    implements UIInput<I, V>, HasClickHandlers<I>, HasMouseHandlers<I>, HasKeyHandlers<I>, HasFocusHandlers<I>, HasChangeHandlers<I> {

	private InputElement       element;

	private boolean            required;

	private static TextBoxImpl impl = GWT.create(TextBoxImpl.class);

	public Input(InputElement element) {
		super(element);
		this.element = element;
	}

	protected InputElement element() {
		return this.element;
	}

	@Override
	public I onClick(ClickHandler handler) {
		return (I)Events.on(this, handler);
	}

	@Override
	public I onDoubleClick(DoubleClickHandler handler) {
		return (I)Events.on(this, handler);
	}

	@Override
	public I onFocus(FocusHandler handler) {
		return (I)Events.on(this, handler);
	}

	@Override
	public I onBlur(BlurHandler handler) {
		return (I)Events.on(this, handler);
	}

	public I onChange(ChangeHandler handler) {
		return (I)Events.on(this, handler);
	}

	@Override
	public I onKeyPress(KeyPressHandler handler) {
		return (I)Events.on(this, handler);
	}

	@Override
	public I onKeyDown(KeyDownHandler handler) {
		return (I)Events.on(this, handler);
	}

	@Override
	public I onKeyUp(KeyUpHandler handler) {
		return (I)Events.on(this, handler);
	}

	@Override
	public I onMouseDown(MouseDownHandler handler) {
		return (I)Events.on(this, handler);
	}

	@Override
	public I onMouseMove(MouseMoveHandler handler) {
		return (I)Events.on(this, handler);
	}

	@Override
	public I onMouseOut(MouseOutHandler handler) {
		return (I)Events.on(this, handler);
	}

	@Override
	public I onMouseOver(MouseOverHandler handler) {
		return (I)Events.on(this, handler);
	}

	@Override
	public I onMouseUp(MouseUpHandler handler) {
		return (I)Events.on(this, handler);
	}

	@Override
	public I onMouseWheel(MouseWheelHandler handler) {
		return (I)Events.on(this, handler);
	}

	public I size(Size size) {
		switch (size) {
			case MINI:
				return this.className("input-mini");
			case SMALL:
				return this.className("input-small");
			case LARGE:
				return this.className("input-large");
			default:
				break;
		}

		return this.className("input-medium");
	}

	public I cursorAt(int index) {
		this.selectRange(index, 0);
		return (I)this;
	}

	public int cursorPosition() {
		return Input.impl.getCursorPos((com.google.gwt.user.client.Element)this.element.cast());
	}

	public I span(int span) {
		return this.className("span" + span);
	}

	public I disable() {
		this.element.setDisabled(true);
		return (I)this;
	}

	public I enable() {
		this.element.setDisabled(false);
		return (I)this;
	}

	@Override
	public boolean isEditable() {
		return !this.isReadOnly();
	}

	public I editable() {
		this.element.setReadOnly(false);
		return (I)this;
	}

	@Override
	public boolean isReadOnly() {
		return this.element.isReadOnly();
	}

	public I readonly() {
		this.element.setReadOnly(true);
		return (I)this;
	}

	@Override
	public boolean isNullable() {
		return !this.isRequired();
	}

	public I nullable() {
		this.required = false;
		return (I)this;
	}

	@Override
	public boolean isRequired() {
		return this.required;
	}

	public I required() {
		this.required = true;
		return (I)this;
	}

	public I placeholder(String placeholder) {
		return this.attribute("placeholder", placeholder);
	}

	public I selectAll() {
		int length = this.element.getValue().length();
		if (length > 0) {
			this.selectRange(0, length);
		}
		return (I)this;
	}

	private void selectRange(int index, int length) {
		Input.impl.setSelectionRange((com.google.gwt.user.client.Element)this.element.cast(), index, length);
	}
}