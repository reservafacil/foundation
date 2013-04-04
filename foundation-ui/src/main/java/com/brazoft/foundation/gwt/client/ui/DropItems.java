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
import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.event.api.HasClickHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasKeyHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasMouseHandlers;
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
import com.google.gwt.user.client.ui.Widget;

public class DropItems extends Bootstrap<DropItems>
{
	public DropItems()
	{
		this(false);
	}
	
	DropItems(boolean subMenu)
	{
		super(ElementResolver.ul());
		this.className("dropdown-menu");
		if(!subMenu)
		{
			this.attribute("role", "menu");
		}
	}
	
	public DropItem item(String label)
	{
		return this.addItem(label);
	}
	
	public DropItems subItem(String label)
	{
		DropItems sub = new DropItems(true);
		
		this.addItem(label).add(sub).className("dropdown-submenu");
		
		return sub;
	}
	
	public DropItems divider()
	{
		return this.add(new DropItem());
	}
	
	DropItem addItem(String label)
	{
		DropItem item = new DropItem(label);
		
		this.add(item);
		
		return item;
	}
	
	public class DropItem extends Bootstrap<DropItem> implements HasFocusHandlers<DropItem>, HasClickHandlers<DropItem>, HasKeyHandlers<DropItem>, HasMouseHandlers<DropItem>
	{
		private HTML<AnchorElement> link = HTML.asAnchor("#");
		
		public DropItem()
		{
			super(ElementResolver.li());
			this.className("divider");
		}
		
		public DropItem(String label)
		{
			super(ElementResolver.li());
			this.init(label);
		}
		
		private void init(String label)
		{
			this.add(this.link);
			this.link.element().setTabIndex(-1);
			this.link.element().setHref("#");
			this.link.text(label);
		}
		
		@Override
		public DropItem onMouseDown(MouseDownHandler handler)
		{
			this.link.onMouseDown(handler);
			return this;
		}

		@Override
		public DropItem onMouseMove(MouseMoveHandler handler)
		{
			this.link.onMouseMove(handler);
			return this;
		}

		@Override
		public DropItem onMouseOut(MouseOutHandler handler)
		{
			this.link.onMouseOut(handler);
			return this;
		}

		@Override
		public DropItem onMouseOver(MouseOverHandler handler)
		{
			this.link.onMouseOver(handler);
			return this;
		}

		@Override
		public DropItem onMouseUp(MouseUpHandler handler)
		{
			this.link.onMouseUp(handler);
			return this;
		}

		@Override
		public DropItem onMouseWheel(MouseWheelHandler handler)
		{
			this.link.onMouseWheel(handler);
			return this;
		}

		@Override
		public DropItem onKeyPress(KeyPressHandler handler)
		{
			this.link.onKeyPress(handler);
			return this;
		}

		@Override
		public DropItem onKeyDown(KeyDownHandler handler)
		{
			this.link.onKeyDown(handler);
			return this;
		}

		@Override
		public DropItem onKeyUp(KeyUpHandler handler)
		{
			this.link.onKeyUp(handler);
			return this;
		}

		@Override
		public DropItem onClick(ClickHandler handler)
		{
			this.link.onClick(handler);
			return this;
		}

		@Override
		public DropItem onDoubleClick(DoubleClickHandler handler)
		{
			this.link.onDoubleClick(handler);
			return this;
		}

		@Override
		public DropItem onFocus(FocusHandler handler)
		{
			this.link.onFocus(handler);
			return this;
		}

		@Override
		public DropItem onBlur(BlurHandler handler)
		{
			this.link.onBlur(handler);
			return this;
		}

		@Override
		public DropItem add(Widget add)
		{
			return super.add(add);
		}
	}
}