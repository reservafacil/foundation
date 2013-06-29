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

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.brazoft.foundation.gwt.client.event.Events;
import com.brazoft.foundation.gwt.client.ui.api.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.*;

final class ToggleButton
    extends Bootstrap<ToggleButton>
    implements UIButton<ToggleButton> {

    private Caret             caret = new Caret();

    private HTML<SpanElement> text  = HTML.asSpan();

    public ToggleButton() {
	super(ElementResolver.a());
	this.init();
    }

    private void init() {
	this.text.style().display(Display.INLINE_BLOCK).marginLeft(4, Unit.PX).marginRight(4, Unit.PX);
	this.add(text).add(caret);
	this.element().setHref("#");
	this.className("dropdown-toggle").attribute("data-toggle", "dropdown");
    }

    protected AnchorElement element() {
	return this.getElement().cast();
    }

    @Override
    public ToggleButton onClick(ClickHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public ToggleButton onDoubleClick(DoubleClickHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public ToggleButton onKeyPress(KeyPressHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public ToggleButton onKeyDown(KeyDownHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public ToggleButton onKeyUp(KeyUpHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public ToggleButton onMouseDown(MouseDownHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public ToggleButton onMouseMove(MouseMoveHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public ToggleButton onMouseOut(MouseOutHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public ToggleButton onMouseOver(MouseOverHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public ToggleButton onMouseUp(MouseUpHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public ToggleButton onMouseWheel(MouseWheelHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public ToggleButton onFocus(FocusHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public ToggleButton onBlur(BlurHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public ToggleButton text(String text) {
	Component.Util.setHTML(this.text, text);
	return this;
    }

    @Override
    public String getText() {
	return Component.Util.getHTML(this.text);
    }

    public ToggleButton size(Size size) {
	return Widgets.setSize(this, size);
    }

    public ToggleButton primary() {
	return Widgets.setPrimary(this);
    }

    public ToggleButton info() {
	return Widgets.setInfo(this);
    }

    public ToggleButton success() {
	return Widgets.setSuccess(this);
    }

    public ToggleButton warning() {
	return Widgets.setWarning(this);
    }

    public ToggleButton danger() {
	return Widgets.setDanger(this);
    }

    public ToggleButton inverse() {
	return Widgets.setInverse(this);
    }

    public ToggleButton link() {
	return Widgets.setLink(this);
    }

    public ToggleButton expanded() {
	return Widgets.setExpanded(this);
    }

    public ToggleButton icon(Icon icon) {
	return Widgets.setIcon(this, icon, true);
    }
}