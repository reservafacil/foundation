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

import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.brazoft.foundation.gwt.client.ui.api.UIButton;
import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
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

public class SplitButton extends Bootstrap<SplitButton> implements UIButton<SplitButton>
{
	private Button main = new Button();
	
	private Button toggle = new Button();
	
	public SplitButton()
	{
		this(DropOptions.DOWN);
	}
	
	public SplitButton(DropOptions option)
	{
		super(ElementResolver.div());
		this.init(option);
	}
	
	private void init(DropOptions option)
	{
		this.className("btn-group");
		
		if(option.equals(DropOptions.UP))
		{
			this.className("dropup");
		}
		
		this.toggle.className("btn dropdown-toggle").attribute("data-toggle", "dropdown");
		this.toggle.add(new Caret());
		
		this.add(main).add(toggle);
	}
	
	@Override
	public SplitButton onClick(ClickHandler handler)
	{
		this.main.onClick(handler);
		return this;
	}

	@Override
	public SplitButton onDoubleClick(DoubleClickHandler handler)
	{
		this.main.onDoubleClick(handler);
		return this;
	}

	@Override
	public SplitButton onFocus(FocusHandler handler)
	{
		this.main.onFocus(handler);
		return this;
	}

	@Override
	public SplitButton onBlur(BlurHandler handler)
	{
		this.main.onBlur(handler);
		return this;
	}

	@Override
	public SplitButton onKeyPress(KeyPressHandler handler)
	{
		this.main.onKeyPress(handler);
		return this;
	}

	@Override
	public SplitButton onKeyDown(KeyDownHandler handler)
	{
		this.main.onKeyDown(handler);
		return this;
	}

	@Override
	public SplitButton onKeyUp(KeyUpHandler handler)
	{
		this.main.onKeyUp(handler);
		return this;
	}

	@Override
	public SplitButton onMouseDown(MouseDownHandler handler)
	{
		this.main.onMouseDown(handler);
		return this;
	}

	@Override
	public SplitButton onMouseMove(MouseMoveHandler handler)
	{
		this.main.onMouseMove(handler);
		return this;
	}

	@Override
	public SplitButton onMouseOut(MouseOutHandler handler)
	{
		this.main.onMouseOut(handler);
		return this;
	}

	@Override
	public SplitButton onMouseOver(MouseOverHandler handler)
	{
		this.main.onMouseOver(handler);
		return this;
	}

	@Override
	public SplitButton onMouseUp(MouseUpHandler handler)
	{
		this.main.onMouseUp(handler);
		return this;
	}

	@Override
	public SplitButton onMouseWheel(MouseWheelHandler handler)
	{
		this.main.onMouseWheel(handler);
		return this;
	}

	public SplitButton menu(DropItems items)
	{
		return this.add(items);
	}

	@Override
	public SplitButton text(String text)
	{
		this.main.text(text);
		return this;
	}

	@Override
	public String getText()
	{
		return this.main.getText();
	}

	@Override
	public SplitButton size(Size size)
	{
		this.main.size(size);
		this.toggle.size(size);
		
		return this;
	}

	@Override
	public SplitButton primary()
	{
		this.main.primary();
		this.toggle.primary();
		
		return this;
	}

	@Override
	public SplitButton info()
	{
		this.main.info();
		this.toggle.info();
		
		return this;
	}

	@Override
	public SplitButton success()
	{
		this.main.success();
		this.toggle.success();
		
		return this;
	}

	@Override
	public SplitButton warning()
	{
		this.main.warning();
		this.toggle.warning();
		
		return this;
	}

	@Override
	public SplitButton danger()
	{
		this.main.danger();
		this.toggle.danger();
		
		return this;
	}

	@Override
	public SplitButton inverse()
	{
		this.main.inverse();
		this.toggle.inverse();
		
		return this;
	}

	@Override
	public SplitButton link()
	{
		this.main.link();
		this.toggle.link();
		
		return this;
	}

	@Override
	public SplitButton expanded()
	{
		this.main.expanded();
		this.toggle.expanded();
		
		return this;
	}

	@Override
	public SplitButton icon(Icon icon)
	{
		this.main.icon(icon);
		
		return this;
	}
}
