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
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.brazoft.foundation.gwt.client.event.api.HasClickHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasKeyHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasMouseHandlers;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.google.gwt.dom.client.AnchorElement;
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

public class NavigationList extends Bootstrap<NavigationList>
{
	public NavigationList()
	{
		super(ElementResolver.ul());
		this.className("nav nav-list");
	}
	
	public NavigationList activate(int index)
	{
		Widgets.activateClass(this.getWidget().getWidget(index));
		return this;
	}
	
	public HeadItem header(String label)
	{
		HeadItem item = new HeadItem();
		this.add(item);
		
		return item;
	}
	
	public ListItem item(String label)
	{
		ListItem item = new ListItem();
		this.add(item);
		
		return item;
	}
	
	public static class HeadItem extends Bootstrap<HeadItem> implements HasText<HeadItem>
	{
		public HeadItem()
		{
			super(ElementResolver.li());
			this.className("nav-header");
		}

		@Override
		public HeadItem text(String text)
		{
			return Component.Util.setHTML(this, text);
		}

		@Override
		public String getText()
		{
			return Component.Util.getHTML(this);
		}
	}
	
	public static class ListItem extends Bootstrap<HeadItem> implements HasText<ListItem>, HasFocusHandlers<ListItem>, HasClickHandlers<ListItem>, HasKeyHandlers<ListItem>, HasMouseHandlers<ListItem>
	{
		private HTML<AnchorElement> link = HTML.asAnchor("#");
		
		public ListItem()
		{
			super(ElementResolver.li());
			this.add(this.link);
			this.link.element().setHref("#");
		}
		
		@Override
		public ListItem onMouseDown(MouseDownHandler handler)
		{
			this.link.onMouseDown(handler);
			return this;
		}

		@Override
		public ListItem onMouseMove(MouseMoveHandler handler)
		{
			this.link.onMouseMove(handler);
			return this;
		}

		@Override
		public ListItem onMouseOut(MouseOutHandler handler)
		{
			this.link.onMouseOut(handler);
			return this;
		}

		@Override
		public ListItem onMouseOver(MouseOverHandler handler)
		{
			this.link.onMouseOver(handler);
			return this;
		}

		@Override
		public ListItem onMouseUp(MouseUpHandler handler)
		{
			this.link.onMouseUp(handler);
			return this;
		}

		@Override
		public ListItem onMouseWheel(MouseWheelHandler handler)
		{
			this.link.onMouseWheel(handler);
			return this;
		}

		@Override
		public ListItem onKeyPress(KeyPressHandler handler)
		{
			this.link.onKeyPress(handler);
			return this;
		}

		@Override
		public ListItem onKeyDown(KeyDownHandler handler)
		{
			this.link.onKeyDown(handler);
			return this;
		}

		@Override
		public ListItem onKeyUp(KeyUpHandler handler)
		{
			this.link.onKeyUp(handler);
			return this;
		}

		@Override
		public ListItem onClick(ClickHandler handler)
		{
			this.link.onClick(handler);
			return this;
		}

		@Override
		public ListItem onDoubleClick(DoubleClickHandler handler)
		{
			this.link.onDoubleClick(handler);
			return this;
		}

		@Override
		public ListItem onFocus(FocusHandler handler)
		{
			this.link.onFocus(handler);
			return this;
		}

		@Override
		public ListItem onBlur(BlurHandler handler)
		{
			this.link.onBlur(handler);
			return this;
		}

		@Override
		public ListItem text(String text)
		{
			this.link.text(text);
			return this;
		}

		@Override
		public String getText()
		{
			return this.link.getText();
		}
	}
}