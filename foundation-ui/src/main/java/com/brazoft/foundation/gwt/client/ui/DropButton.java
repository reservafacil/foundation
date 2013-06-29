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
import com.brazoft.foundation.gwt.client.ui.api.*;
import com.google.gwt.event.dom.client.*;

public final class DropButton
    extends Bootstrap<DropButton>
    implements UIButton<DropButton> {

    private ToggleButton toggle = new ToggleButton().className("btn");

    public DropButton() {
	this(DropOptions.DOWN);
    }

    public DropButton(DropOptions option) {
	super(ElementResolver.div());
	this.init(option);
    }

    private void init(DropOptions option) {
	this.className("btn-group");
	if (option.equals(DropOptions.UP)) {
	    this.className("dropup");
	}
	this.add(this.toggle);
    }

    @Override
    public DropButton onClick(ClickHandler handler) {
	this.toggle.onClick(handler);
	return this;
    }

    @Override
    public DropButton onDoubleClick(DoubleClickHandler handler) {
	this.toggle.onDoubleClick(handler);
	return this;
    }

    @Override
    public DropButton onKeyPress(KeyPressHandler handler) {
	this.toggle.onKeyPress(handler);
	return this;
    }

    @Override
    public DropButton onKeyDown(KeyDownHandler handler) {
	this.toggle.onKeyDown(handler);
	return this;
    }

    @Override
    public DropButton onKeyUp(KeyUpHandler handler) {
	this.toggle.onKeyUp(handler);
	return this;
    }

    @Override
    public DropButton onMouseDown(MouseDownHandler handler) {
	this.toggle.onMouseDown(handler);
	return this;
    }

    @Override
    public DropButton onMouseMove(MouseMoveHandler handler) {
	this.toggle.onMouseMove(handler);
	return this;
    }

    @Override
    public DropButton onMouseOut(MouseOutHandler handler) {
	this.toggle.onMouseOut(handler);
	return this;
    }

    @Override
    public DropButton onMouseOver(MouseOverHandler handler) {
	this.toggle.onMouseOver(handler);
	return this;
    }

    @Override
    public DropButton onMouseUp(MouseUpHandler handler) {
	this.toggle.onMouseUp(handler);
	return this;
    }

    @Override
    public DropButton onMouseWheel(MouseWheelHandler handler) {
	this.toggle.onMouseWheel(handler);
	return this;
    }

    @Override
    public DropButton onFocus(FocusHandler handler) {
	this.toggle.onFocus(handler);
	return this;
    }

    @Override
    public DropButton onBlur(BlurHandler handler) {
	this.toggle.onBlur(handler);
	return this;
    }

    @Override
    public String getText() {
	return this.toggle.getText();
    }

    public DropButton menu(DropItems menu) {
	return this.add(menu);
    }

    @Override
    public DropButton text(String text) {
	this.toggle.text(text);

	return this;
    }

    public DropButton size(Size size) {
	this.toggle.size(size);
	return this;
    }

    public DropButton primary() {
	this.toggle.primary();
	return this;
    }

    public DropButton info() {
	this.toggle.info();
	return this;
    }

    public DropButton success() {
	this.toggle.success();
	return this;
    }

    public DropButton warning() {
	this.toggle.warning();
	return this;
    }

    public DropButton danger() {
	this.toggle.danger();
	return this;
    }

    public DropButton inverse() {
	this.toggle.inverse();
	return this;
    }

    public DropButton link() {
	this.toggle.link();
	return this;
    }

    public DropButton expanded() {
	this.toggle.expanded();
	return this;
    }

    public DropButton icon(Icon icon) {
	this.toggle.icon(icon);
	return this;
    }
}