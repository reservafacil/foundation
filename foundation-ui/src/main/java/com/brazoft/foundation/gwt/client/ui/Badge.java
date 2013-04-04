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
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.brazoft.foundation.gwt.client.event.Events;
import com.brazoft.foundation.gwt.client.event.api.HasClickHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasMouseHandlers;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;

public class Badge extends Bootstrap<Badge> implements HasText<Badge>, HasClickHandlers<Badge>, HasMouseHandlers<Badge>
{
	public Badge()
	{
		super(ElementResolver.span());
		this.className("badge");
	}
	
	@Override
	public Badge onClick(ClickHandler handler)
	{
		return Events.on(this, handler);
	}
	
	@Override
	public Badge onDoubleClick(DoubleClickHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public Badge onMouseDown(MouseDownHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public Badge onMouseMove(MouseMoveHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public Badge onMouseOut(MouseOutHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public Badge onMouseOver(MouseOverHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public Badge onMouseUp(MouseUpHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public Badge onMouseWheel(MouseWheelHandler handler)
	{
		return Events.on(this, handler);
	}

	public Badge success()
	{
		return this.className("badge-success");
	}
	
	public Badge warning()
	{
		return this.className("badge-warning");
	}
	
	public Badge important()
	{
		return this.className("badge-important");
	}
	
	public Badge info()
	{
		return this.className("badge-info");
	}
	
	public Badge inverse()
	{
		return this.className("badge-inverse");
	}

	@Override
	public Badge text(String text)
	{
		return Component.Util.setHTML(this, text);
	}

	@Override
	public String getText()
	{
		return Component.Util.getHTML(this);
	}
}
