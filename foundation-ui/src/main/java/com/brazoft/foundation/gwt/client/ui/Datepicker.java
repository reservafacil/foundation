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

import java.util.Date;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.event.Event;
import com.brazoft.foundation.gwt.client.event.api.EventHandler;
import com.brazoft.foundation.gwt.client.event.api.HasChangeHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.i18n.DateFormat;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.brazoft.foundation.gwt.client.util.Calendar;
import com.brazoft.foundation.gwt.client.util.MobileUtil;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.i18n.client.DateTimeFormat;

public final class Datepicker
    extends Bootstrap<Datepicker>
    implements UIInput<Datepicker, Date>, HasChangeHandlers<Datepicker>, HasFocusHandlers<Datepicker> {

	private TextBox          input;

	private MonthPanel       panel;

	private HTML<DivElement> picker = HTML.asDiv().className("datepicker");

	private DateFormat       format;
	private DateTimeFormat formatMobile;

	private boolean          shown;

	private boolean          readOnly;

	private POSITION		 position = POSITION.RIGHT;

	public Datepicker() {
		this(DateFormat.DATE_SHORT);
	}

	public Datepicker(DateFormat format) {
		super(ElementResolver.div());
		this.init(format);
	}

	private void init(DateFormat format) {
		this.style().position(Position.RELATIVE);
		this.format = format;
		this.formatMobile = DateTimeFormat.getFormat("yyyy-MM-dd");

		this.input = new TextBox();
		this.add(this.input);
		this.editable();

		if (MobileUtil.isMobileSafari()) {
			this.input.input().getElement().setAttribute("type", "date");
			return;
		}

		this.input.onFocus(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {
				Datepicker.this.show();
			}
		});
		this.input.onBlur(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				if (Datepicker.this.panel.isSelected()) {
					return;
				}

				Datepicker.this.hide();
			}
		});

		this.panel = new MonthPanel();
		this.panel.onSelection(new EventHandler<Date>() {

			@Override
			public void onEvent(Event<Date> e) {
				Datepicker.this.value(e.data());
				Datepicker.this.hide();
			}
		}).onNext(this.doFocus).onPrevious(this.doFocus);
		this.add(this.picker.add(this.panel)).hide();
	}

	public Datepicker icon() {
		Button icon = new Button().icon(Icon.CALENDAR);
		icon.onClick(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Datepicker.this.input.focus();
			}
		});

		this.input.append(icon);

		return this;
	}
	
	public Datepicker append(Button button){
		this.input.append(button);
		return this;
	}
	
	public Datepicker prepend(Button button){
		this.input.prepend(button);
		return this;
	}

	public InputText input() {
		if (MobileUtil.isMobileSafari()) {
			return new InputText();
		} else {
			return input.input();
		}
	}

	@Override
	public Datepicker onChange(ChangeHandler handler) {
		if (!MobileUtil.isMobileSafari()) { this.input.onChange(handler); }
		return this;
	}

	@Override
	public Datepicker onFocus(FocusHandler handler) {
		if (!MobileUtil.isMobileSafari()) { this.input.onFocus(handler); }
		return this;
	}

	@Override
	public Datepicker onBlur(BlurHandler handler) {
		if (!MobileUtil.isMobileSafari()) { this.input.onBlur(handler); }
		return this;
	}

	public Datepicker onSelection(EventHandler<Date> handler) {
		if (!MobileUtil.isMobileSafari()) { this.panel.onSelection(handler); }
		return this;
	}

	public Datepicker block() {
		this.input.block();
		return this;
	}

	public Datepicker span(int span) {
		this.input.span(span);
		return this;
	}

	public Datepicker size(Size size) {
		this.input.size(size);
		return this;
	}

	public Datepicker range(Date start, Date end) {
		if (MobileUtil.isMobileSafari()) { return this; } 
		this.panel.range(start, end);

		if (Calendar.as(start).after(this.panel.current())) {
			this.value(new Date(start.getTime()));
		}

		return this;
	}

	public Datepicker focus() {
		this.input.focus();
		return this;
	}

	Datepicker toggle() {
		Display display = this.picker.style().getDisplay().equals(Display.NONE.getCssName()) ? Display.BLOCK : Display.NONE;
		this.picker.style().display(display);

		return this.toPosition();
	}

	public Datepicker show() {
		if (!this.shown && !this.isReadOnly()) {
			this.picker.style().display(Display.BLOCK);

			this.shown = true;
			return this.toPosition();
		}

		return this;
	}

	public Datepicker hide() {
		this.shown = false;
		this.picker.hidden();
		if (!MobileUtil.isMobileSafari()) { this.panel.selected(false); }

		return this;
	}

	@Override
	public Datepicker clear() {
		this.input.clear();
		return this;
	}

	@Override
	public Datepicker value(Date value) {
		if (value == null) {
			this.input.value(null);
			return this;
		}

		if (!MobileUtil.isMobileSafari()) {
			this.panel.set(value);
		}
		this.input.value(this.format.format(value));

		return this;
	}

	@Override
	public Date getValue() {
		if (this.input.getValue().isEmpty()) {
			return null;
		}
		return MobileUtil.isMobileSafari() ? this.formatMobile.parse(this.input.getValue()) : this.format.unformat(this.input.getValue());
	}

	@Override
	public Datepicker placeholder(String placeholder) {
		this.input.placeholder(placeholder);

		return this;
	}

	@Override
	public boolean isReadOnly() {
		return this.readOnly;
	}

	@Override
	public Datepicker readonly() {
		this.input.input().readonly().style().clearBackgroundColor();
		this.readOnly = true;
		return this;
	}

	@Override
	public boolean isEditable() {
		return !this.readOnly;
	}

	@Override
	public Datepicker editable() {
		this.input.input().style().backgroundColor("white");
		this.readOnly = false;
		return this;
	}

	@Override
	public boolean isNullable() {
		return this.input.isNullable();
	}

	@Override
	public Datepicker nullable() {
		this.input.nullable();
		return this;
	}

	@Override
	public boolean isRequired() {
		return this.input.isRequired();
	}

	@Override
	public Datepicker required() {
		this.input.required();
		return this;
	}

	@Override
	public Datepicker removeHandlers(Event<?> event) {
		if (MobileUtil.isMobileSafari()) { return this; }
		this.panel.removeHandlers(event);
		return super.removeHandlers(event);
	}

	@Override
	public <H extends com.google.gwt.event.shared.EventHandler> Datepicker removeHandlers(Type<H> type) {
		if (MobileUtil.isMobileSafari()) { return this; }
		this.panel.removeHandlers(type);
		return super.removeHandlers(type);
	}

	public Datepicker setPosition(POSITION position) {
		this.position = position;
		return this;
	}

	public enum POSITION {
		LEFT(-138), RIGHT(0);
		private int valor;
		private POSITION(int valor) {
			this.valor = valor;
		}
		public int getPosition() {
			return this.valor;
		}
	}

	private ClickHandler doFocus = new ClickHandler() {

		                             @Override
		                             public void onClick(ClickEvent event) {
			                             Datepicker.this.focus();
		                             }
	                             };

	Datepicker toPosition() {
		InputText input = this.input.input();

		double top = input.top() + input.position().top() + input.scrollTop() + input.outerHeight(true);
		this.picker.style().zIndex(10000).position(Position.ABSOLUTE).display(Display.BLOCK).top(top, Unit.PX).left(this.position.getPosition(), Unit.PX);

		return this;
	}
}