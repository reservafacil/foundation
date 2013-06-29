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

import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.event.Events;
import com.brazoft.foundation.gwt.client.event.api.HasChangeHandlers;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Row;
import com.google.gwt.event.dom.client.*;

public final class HorizontalSpinner
    extends Composite<HorizontalSpinner>
    implements UIInput<HorizontalSpinner, Integer>, HasChangeHandlers<HorizontalSpinner> {

    private Table     table        = new Table();

    private NumberBox input        = new NumberBox().size(Size.MINI).maxLength(3);

    private Iconic    down         = new Iconic().icon(Icon.CHEVRON_LEFT);

    private Iconic    up           = new Iconic().icon(Icon.CHEVRON_RIGHT);

    private int       step         = 1;

    private int       defaultValue = 0;

    private int       minValue     = Integer.MIN_VALUE;

    private int       maxValue     = Integer.MAX_VALUE;

    public HorizontalSpinner() {
	this.initWidget(this.table);
	this.init();
    }

    private void init() {
	this.value(this.defaultValue);

	Row row = this.table.body().row();

	row.cell().add(this.down);
	row.cell().add(this.input);
	row.cell().add(this.up);

	this.up.onClick(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		HorizontalSpinner.this.increment();
	    }
	});

	this.down.onClick(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		HorizontalSpinner.this.decrement();
	    }
	});

	this.input.onBlur(new BlurHandler() {

	    @Override
	    public void onBlur(BlurEvent event) {
		if (HorizontalSpinner.this.input.getValue().isEmpty()) {
		    HorizontalSpinner.this.value(HorizontalSpinner.this.defaultValue);
		    return;
		}

		HorizontalSpinner.this.value(HorizontalSpinner.this.getValue());
	    }
	});
    }

    public NumberBox input() {
	return this.input;
    }

    public HorizontalSpinner onChange(ChangeHandler handler) {
	Events.on(this.input, handler);
	return this;
    }

    public HorizontalSpinner range(int minValue, int maxValue) {
	this.minValue = minValue;
	this.maxValue = maxValue;

	return this;
    }

    public HorizontalSpinner defaultValue(int value) {
	this.defaultValue = value;
	return this.value(value);
    }

    public HorizontalSpinner step(int step) {
	this.step = step;
	return this;
    }

    public HorizontalSpinner increment() {
	int value = this.getValue() + this.step;

	value = Math.min(value, this.maxValue);

	return this.value(value);
    }

    public HorizontalSpinner decrement() {
	int value = this.getValue() - this.step;

	value = Math.max(value, this.minValue);

	return this.value(value);
    }

    public HorizontalSpinner maxLength(int maxLength) {
	this.input.maxLength(maxLength);
	return this;
    }

    public HorizontalSpinner value(Integer value) {
	value = Math.max(value, this.minValue);
	value = Math.min(value, this.maxValue);

	this.input.value(String.valueOf(value));
	return this;
    }

    @Override
    public HorizontalSpinner clear() {
	this.input.value("");
	return this;
    }

    public Integer getValue() {
	return Integer.valueOf(this.input.getValue());
    }

    public HorizontalSpinner block() {
	this.input.block();
	return this;
    }

    @Override
    public HorizontalSpinner placeholder(String placeholder) {
	this.input.placeholder(placeholder);
	return this;
    }

    @Override
    public boolean isReadOnly() {
	return this.input.isReadOnly();
    }

    @Override
    public HorizontalSpinner readonly() {
	this.input.readonly();
	return this;
    }

    @Override
    public boolean isEditable() {
	return this.input.isEditable();
    }

    @Override
    public HorizontalSpinner editable() {
	this.input.editable();
	return this;
    }

    @Override
    public boolean isNullable() {
	return this.input.isNullable();
    }

    @Override
    public HorizontalSpinner nullable() {
	this.input.nullable();
	return this;
    }

    @Override
    public boolean isRequired() {
	return this.input.isRequired();
    }

    @Override
    public HorizontalSpinner required() {
	this.input.required();
	return this;
    }
}