/**
 * Copyright (C) 2009-2012 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brazoft.foundation.gwt.client.ui;

import java.util.Date;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.ResponsiveComponent;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.event.Event;
import com.brazoft.foundation.gwt.client.event.api.EventHandler;
import com.brazoft.foundation.gwt.client.i18n.DateFormat;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.brazoft.foundation.gwt.client.util.Calendar;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.ui.Widget;

public class Datepicker extends Bootstrap<Datepicker> implements UIInput<Datepicker, Date>, ResponsiveComponent<Datepicker>
{
	private ExtendedTextBox input;
	
	private MonthPanel panel;
	
	private HTML<DivElement> picker = HTML.asDiv().className("datepicker");
	
	private DateFormat format;
	
	private boolean shown;
	
	private boolean readOnly;
	
	public Datepicker()
	{
		this(DateFormat.DATE_SHORT);
	}
	
	public Datepicker(DateFormat format)
	{
		super(ElementResolver.div());
		this.init(format);
	}
	
	private void init(DateFormat format)
	{
		this.format = format;
		Button icon = new Button().icon(Icon.CALENDAR);
		icon.onClick(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				Datepicker.this.input.focus();
			}
		});
		this.input = new ExtendedTextBox().append(icon).readonly();
		this.editable();
		this.input.onFocus(new FocusHandler()
		{
			@Override
			public void onFocus(FocusEvent event)
			{
				Datepicker.this.show();
			}
		});
		this.input.onBlur(new BlurHandler()
		{
			@Override
			public void onBlur(BlurEvent event)
			{
				if(Datepicker.this.panel.isSelected())
				{
					return;
				}
				
				Datepicker.this.hide();
			}
		});
		this.add(this.input);
		
		this.panel = new MonthPanel();
		this.panel.onSelection(new EventHandler()
		{
			@Override
			public void onEvent(Event e)
			{
				Datepicker.this.value(((Date) e.data()));
				Datepicker.this.hide();
			}
		}).onNext(this.doFocus).onPrevious(this.doFocus);
		this.add(this.picker.add(this.panel));
		this.hide();
	}
	
	private ClickHandler doFocus = new ClickHandler()
	{
		@Override
		public void onClick(ClickEvent event)
		{
			Datepicker.this.focus();
		}
	};
	
	@Override
	public Datepicker adaptSize(Widget container)
	{
		this.input.adaptSize(container);
		return this;
	}
	
	public Datepicker block()
	{
		this.input.block();
		return this;
	}
	
	public Datepicker span(int span)
	{
		this.input.span(span);
		return this;
	}
	
	public Datepicker size(Size size)
	{
		this.input.size(size);
		return this;
	}
	
	public Datepicker onSelection(EventHandler handler)
	{
		this.panel.onSelection(handler);
		return this;
	}
	
	public static Date getValue(Event event)
	{
		return MonthPanel.getValue(event);
	}
	
	public Datepicker range(Date start, Date end)
	{
		if(Calendar.as(start).after(this.panel.current()))
		{
			this.panel.range(start, end);
			this.value(new Date(start.getTime()));
			return this;
		}
		
		this.panel.range(start, end);
		this.value(this.getValue());
		
		return this;
	}
	
	public Datepicker focus()
	{
		this.input.focus();
		return this;
	}
	
	Datepicker toggle()
	{
		Display display = this.picker.style().getDisplay().equals(Display.NONE.getCssName()) ? Display.BLOCK : Display.NONE;
		this.picker.style().display(display);
		
		return this.position();
	}
	
	Datepicker show()
	{
		if(!this.shown && !this.isReadOnly())
		{
			this.picker.style().display(Display.BLOCK);
			
			this.shown = true;
			return this.position();
		}
		
		return this;
	}
	
	Datepicker hide()
	{
		this.shown = false;
		this.picker.style().display(Display.NONE);
		this.panel.selected(false);
		
		return this;
	}
	
	@Override
	public Datepicker clear()
	{
		this.input.clear();
		return this;
	}
	
	@Override
	public Datepicker value(Date value)
	{
		if(value == null)
		{
			this.input.value(null);
			return this;
		}
		
		this.panel.set(value);
		this.input.value(this.format.format(value));
		
		return this;
	}

	@Override
	public Date getValue()
	{
		return this.format.unformat(this.input.getValue());
	}

	@Override
	public Datepicker placeholder(String placeholder)
	{
		this.input.placeholder(placeholder);
		
		return this;
	}
	
	@Override
	public boolean isReadOnly()
	{
		return this.readOnly;
	}

	@Override
	public Datepicker readonly()
	{
		this.input.getInput().style().clearBackgroundColor();
		this.readOnly = true;
		return this;
	}
	
	@Override
	public boolean isEditable()
	{
		return !this.readOnly;
	}

	@Override
	public Datepicker editable()
	{
		this.input.getInput().style().backgroundColor("white");
		this.readOnly = false;
		return this;
	}
	
	@Override
	public boolean isNullable()
	{
		return this.input.isNullable();
	}

	@Override
	public Datepicker nullable()
	{
		this.input.nullable();
		return this;
	}
	
	@Override
	public boolean isRequired()
	{
		return this.input.isRequired();
	}

	@Override
	public Datepicker required()
	{
		this.input.required();
		return this;
	}
	
	public Datepicker responsiveTo(Widget container)
	{
		this.input.responsiveTo(container);
		return this;
	}
	
	Datepicker position()
	{
		TextBox box = this.input.getInput();
		//double left = box.getAbsoluteLeft() - (box.getOffsetWidth() + box.getElement().getScrollLeft());
		//double top = this.picker.getAbsoluteTop() - this.picker.getElement().getClientHeight() - this.picker.getOffsetHeight();

		double left = 0;
		double top = 0;
		this.picker.style().zIndex(1000).position(Position.RELATIVE).left(left, Unit.PX).top(top, Unit.PX).display(Display.INLINE_BLOCK);
		
		return this;
	}
}