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

import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.event.Events;
import com.brazoft.foundation.gwt.client.event.api.HasChangeHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasKeyHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasMouseHandlers;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.google.gwt.dom.client.InputElement;
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
public abstract class Input<W extends Widget, V> extends Bootstrap<W> implements UIInput<W, V>, HasMouseHandlers<W>, HasKeyHandlers<W>, HasFocusHandlers<W>, HasChangeHandlers<W>
{
	private InputElement element;
	
	private boolean required;
	
	public Input(InputElement element)
	{
		super(element);
		this.element = element;
	}
	
	protected InputElement element()
	{
		return this.element;
	}
	
	@Override
	public W onFocus(FocusHandler handler)
	{
		return (W) Events.on(this, handler);
	}

	@Override
	public W onBlur(BlurHandler handler)
	{
		return (W) Events.on(this, handler);
	}

	public W onChange(ChangeHandler handler)
	{
		return (W) Events.on(this, handler);
	}
	
	@Override
	public W onKeyPress(KeyPressHandler handler)
	{
		return (W) Events.on(this, handler);
	}

	@Override
	public W onKeyDown(KeyDownHandler handler)
	{
		return (W) Events.on(this, handler);
	}

	@Override
	public W onKeyUp(KeyUpHandler handler)
	{
		return (W) Events.on(this, handler);
	}

	@Override
	public W onMouseDown(MouseDownHandler handler)
	{
		return (W) Events.on(this, handler);
	}

	@Override
	public W onMouseMove(MouseMoveHandler handler)
	{
		return (W) Events.on(this, handler);
	}

	@Override
	public W onMouseOut(MouseOutHandler handler)
	{
		return (W) Events.on(this, handler);
	}

	@Override
	public W onMouseOver(MouseOverHandler handler)
	{
		return (W) Events.on(this, handler);
	}

	@Override
	public W onMouseUp(MouseUpHandler handler)
	{
		return (W) Events.on(this, handler);
	}

	@Override
	public W onMouseWheel(MouseWheelHandler handler)
	{
		return (W) Events.on(this, handler);
	}

	public W size(Size size)
	{
		switch(size)
		{
			case MINI: return this.className("input-mini");
			case SMALL: return this.className("input-small");
			case LARGE: return this.className("input-large");
			default: break;
		}
		
		return this.className("input-medium");
	}
	
	public W blur()
	{
		this.element.blur();
		
		return (W) this;
	}
	
	public W focus()
	{
		this.element.focus();
		
		return (W) this;
	}
	
	public W span(int span)
	{
		return this.className("span" + span);
	}
	
	public W disable()
	{
		this.element.setDisabled(true);
		return (W) this;
	}
	
	public W enable()
	{
		this.element.setDisabled(false);
		return (W) this;
	}
	
	@Override
	public boolean isEditable()
	{
		return !this.isReadOnly();
	}
	
	public W editable()
	{
		this.element.setReadOnly(false);
		return (W) this;
	}
	
	@Override
	public boolean isReadOnly()
	{
		return this.element.isReadOnly();
	}
	
	public W readonly()
	{
		this.element.setReadOnly(true);
		return (W) this;
	}
	
	@Override
	public boolean isNullable()
	{
		return !this.isRequired();
	}
	
	public W nullable()
	{
		this.required = false;
		return (W) this;
	}
	
	@Override
	public boolean isRequired()
	{
		return this.required;
	}
	
	public W required()
	{
		this.required = true;
		return (W) this;
	}
	
	public W placeholder(String placeholder)
	{
		return this.attribute("placeholder", placeholder);
	}
}