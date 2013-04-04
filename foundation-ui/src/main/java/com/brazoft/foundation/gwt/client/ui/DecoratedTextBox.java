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

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.brazoft.foundation.gwt.client.component.api.ResponsiveComponent;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.event.api.HasChangeHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasKeyHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasMouseHandlers;
import com.brazoft.foundation.gwt.client.json.JSONCollection;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
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
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public abstract class DecoratedTextBox<W extends Widget, V> extends Bootstrap<W> implements UIInput<W, V>, HasKeyHandlers<W>, HasFocusHandlers<W>, HasMouseHandlers<W>, HasChangeHandlers<W>, ResponsiveComponent<W>
{
	private TextBox input = new TextBox();
	
	public enum Decoration
	{
		APPENDED,
		PREPENDED;
		
		String className()
		{
			if(this.equals(APPENDED))
			{
				return "input-append";
			}
			
			return "input-prepend";
		}
	}
	
	public DecoratedTextBox()
	{
		super(ElementResolver.div());
		this.add(this.input);
	}
	
	public W onChange(ChangeHandler handler)
	{
		this.input.onChange(handler);
		return (W) this;
	}
	
	@Override
	public W onMouseDown(MouseDownHandler handler)
	{
		this.input.onMouseDown(handler);
		return (W) this;
	}

	@Override
	public W onMouseMove(MouseMoveHandler handler)
	{
		this.input.onMouseMove(handler);
		return (W) this;
	}

	@Override
	public W onMouseOut(MouseOutHandler handler)
	{
		this.input.onMouseOut(handler);
		return (W) this;
	}

	@Override
	public W onMouseOver(MouseOverHandler handler)
	{
		this.input.onMouseOver(handler);
		return (W) this;
	}

	@Override
	public W onMouseUp(MouseUpHandler handler)
	{
		this.input.onMouseUp(handler);
		return (W) this;
	}

	@Override
	public W onMouseWheel(MouseWheelHandler handler)
	{
		this.input.onMouseWheel(handler);
		return (W) this;
	}

	@Override
	public W onFocus(FocusHandler handler)
	{
		this.input.onFocus(handler);
		return (W) this;
	}

	@Override
	public W onBlur(BlurHandler handler)
	{
		this.input.onBlur(handler);
		return (W) this;
	}

	@Override
	public W onKeyPress(KeyPressHandler handler)
	{
		this.input.onKeyPress(handler);
		return (W) this;
	}

	@Override
	public W onKeyDown(KeyDownHandler handler)
	{
		this.input.onKeyDown(handler);
		return (W) this;
	}

	@Override
	public W onKeyUp(KeyUpHandler handler)
	{
		this.input.onKeyUp(handler);
		return (W) this;
	}

	public W typeahead(JSONArray values)
	{
		this.input.attribute("data-source", values.toString()).attribute("data-provide", "typeahead");
		return (W) this;
	}
	
	public W typeahead(JSONArray values, int showItems)
	{
		this.input.attribute("data-source", values.toString()).attribute("data-provide", "typeahead").attribute("data-items", String.valueOf(showItems));
		return (W) this;
	}
	
	public W mask(String pattern)
	{
		this.input.mask(pattern);
		
		return (W) this; 
	}
	
	public W mask(String placeholder, String pattern)
	{
		this.input.mask(pattern, placeholder);
		return (W) this;
	}
	
	public W typeahead(JSONCollection<?> values)
	{
		this.input.typeahead(values);
		return (W) this;
	}
	
	public W typeahead(JSONCollection<?> values, int showItems)
	{
		this.input.typeahead(values, showItems);
		return (W) this;
	}
	
	public W blur()
	{
		this.input.blur();
		
		return (W) this;
	}
	
	public W focus()
	{
		this.input.focus();
		
		return (W) this;
	}
	
	public W span(int span)
	{
		this.input.span(span);
		return (W) this;
	}
	
	public W placeholder(String placeholder)
	{
		this.input.placeholder(placeholder);
		
		return (W) this;
	}
	
	public W size(Size size)
	{
		switch (size)
		{
			case LARGE:
				this.input.className("input-large");
				return (W) this;
			case MINI:
				this.input.className("input-mini");
				return (W) this;
			case SMALL:
				this.input.className("input-small");
				return (W) this;
			default:
				break;
		}
		
		this.input.className("input-medium");
		return (W) this;
	}
	
	@Override
	public boolean isReadOnly()
	{
		return this.input.isReadOnly();
	}
	
	@Override
	public W readonly()
	{
		this.input.readonly();
		return (W) this;
	}
	
	@Override
	public boolean isEditable()
	{
		return this.input.isEditable();
	}

	@Override
	public W editable()
	{
		this.input.editable();
		return (W) this;
	}
	
	@Override
	public boolean isNullable()
	{
		return this.input.isNullable();
	}

	@Override
	public W nullable()
	{
		this.input.nullable();
		return (W) this;
	}
	
	@Override
	public boolean isRequired()
	{
		return this.input.isRequired();
	}

	@Override
	public W required()
	{
		this.input.required();
		return (W) this;
	}
	
	public W block()
	{
		this.input.block();
		return (W) this;
	}
	
	public W responsiveTo(final Widget container)
	{
		return Component.Util.responsiveBehavior(this, container);
	}
	
	public W adaptSize(Widget container)
	{
		double addOnWidth = 0;
		double containerWidth = Component.Util.innerWidth(container);
		
		for(Widget child : this.getChildren())
		{
			if(!child.equals(this.input))
			{
				addOnWidth += child.getOffsetWidth();
			}
		}
		
		double inputWidth = containerWidth - addOnWidth - Component.Util.computeLeft(container) - Component.Util.computeRight(this.input) - Component.Util.computeLeft(this.input);
				
		this.input.style().width(inputWidth, Unit.PX);
		
		return (W) this;
	}
	
	TextBox input()
	{
		return this.input;
	}
	
	@Override
	protected W add(Widget add)
	{
		return super.add(add);
	}
	
	W add(Icon icon, Decoration decoration)
	{
		HTML<SpanElement> addOn = HTML.asSpan().className("add-on");
		Widgets.setIcon(addOn, icon);
		
		return this.add(addOn, decoration);
	}
	
	W add(String text, Decoration decoration)
	{
		HTML<SpanElement> addOn = HTML.asSpan().className("add-on").text(text);

		return this.add(addOn, decoration);
	}
	
	W add(Widget addOn, Decoration decoration)
	{
		this.className(decoration.className());
		
		if(decoration.equals(Decoration.PREPENDED))
		{
			return this.insert(addOn, this.input);
		}
		
		return this.add(addOn);
	}
}