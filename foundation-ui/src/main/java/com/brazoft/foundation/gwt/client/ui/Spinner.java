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
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;

public final class Spinner
    extends Bootstrap<Spinner>
    implements UIInput<Spinner, Integer>, HasChangeHandlers<Spinner> {

    private InputText      input        = new InputText().size(Size.MINI).className("spinner-input").maxLength(3);

    private SpinnerButtons buttons      = new SpinnerButtons();

    private int            step         = 1;

    private int            defaultValue = 1;

    private int            minValue     = Integer.MIN_VALUE;

    private int            maxValue     = Integer.MAX_VALUE;

    public Spinner() {
	super(ElementResolver.div());
	this.init();
    }

    private void init() {
	this.value(this.defaultValue);
	this.className("spinner").add(this.input).add(this.buttons);

	this.input.onKeyPress(new KeyPressHandler() {

	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (!"0123456789".contains(String.valueOf(event.getCharCode()))) {
		    event.preventDefault();
		}
	    }
	});

	this.buttons.onUp(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		Spinner.this.increment();
	    }
	});

	this.buttons.onDown(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		Spinner.this.decrement();
	    }
	});

	this.input.onBlur(new BlurHandler() {

	    @Override
	    public void onBlur(BlurEvent event) {
		if (Spinner.this.input.getValue().isEmpty()) {
		    Spinner.this.value(Spinner.this.defaultValue);
		}
	    }
	});
    }

    public Spinner onChange(ChangeHandler handler) {
	Events.on(this.input, handler);
	return this;
    }

    public Spinner range(int minValue, int maxValue) {
	this.minValue = minValue;
	this.maxValue = maxValue;

	return this;
    }

    public Spinner defaultValue(int value) {
	this.defaultValue = value;
	return this.value(value);
    }

    public Spinner step(int step) {
	this.step = step;
	return this;
    }

    public Spinner increment() {
	int value = this.getValue() + this.step;

	value = value > this.maxValue ? this.maxValue : value;

	return this.value(value);
    }

    public Spinner decrement() {
	int value = this.getValue() - this.step;

	value = value < this.minValue ? this.minValue : value;

	return this.value(value);
    }

    public Spinner maxLength(int maxLength) {
	this.input.maxLength(maxLength);
	return this;
    }

    public Spinner value(Integer value) {
	this.input.value(String.valueOf(value));
	return this;
    }

    @Override
    public Spinner clear() {
	this.input.value("");
	return this;
    }

    public Integer getValue() {
	return Integer.valueOf(this.input.getValue());
    }

    public Spinner block() {
	return this.className("input-block-level");
    }

    @Override
    public Spinner placeholder(String placeholder) {
	this.input.placeholder(placeholder);
	return this;
    }

    @Override
    public boolean isReadOnly() {
	return this.input.isReadOnly();
    }

    @Override
    public Spinner readonly() {
	this.input.readonly();
	return this;
    }

    @Override
    public boolean isEditable() {
	return this.input.isEditable();
    }

    @Override
    public Spinner editable() {
	this.input.editable();
	return this;
    }

    @Override
    public boolean isNullable() {
	return this.input.isNullable();
    }

    @Override
    public Spinner nullable() {
	this.input.nullable();
	return this;
    }

    @Override
    public boolean isRequired() {
	return this.input.isRequired();
    }

    @Override
    public Spinner required() {
	this.input.required();
	return this;
    }

    class SpinnerButtons
	extends Bootstrap<SpinnerButtons> {

	private Button up   = new Button().className("spinner-up").icon(Icon.CHEVRON_UP);

	private Button down = new Button().className("spinner-down").icon(Icon.CHEVRON_DOWN);

	public SpinnerButtons() {
	    super(ElementResolver.div());
	    this.className("spinner-buttons  btn-group btn-group-vertical").add(this.up).add(this.down);
	}

	public SpinnerButtons onUp(ClickHandler handler) {
	    Events.on(this.up, handler);
	    return this;
	}

	public SpinnerButtons onDown(ClickHandler handler) {
	    Events.on(this.down, handler);
	    return this;
	}
    }
}