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
import com.brazoft.foundation.gwt.client.component.api.HasText;
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

public class Pills extends Bootstrap<Pills>
{
	public Pills()
	{
		this(NavOptions.DEFAULT);
	}
	
	public Pills(NavOptions option)
	{
		super(ElementResolver.ul());
		this.className("nav nav-pills");
		if (option.equals(NavOptions.STACKED))
		{
			this.className("nav-stacked");
		}
	}
	
	public PillItem dropItem(DropItems items)
	{
		return this.dropItem("", items);
	}
	
	public PillItem dropItem(String label, DropItems items)
	{
		return this.newItem().toggle().text(label).add(items).className("dropdown");
	}
	
	public PillItem item()
	{
		return this.item("");
	}

	public PillItem item(String label)
	{
		return this.newItem().link().text(label);
	}
	
	PillItem newItem()
	{
		PillItem item = new PillItem(); 
		this.add(item);
		
		return item;
	}
	
	public static class PillItem extends Bootstrap<PillItem> implements HasText<PillItem>, HasClickHandlers<PillItem>, HasMouseHandlers<PillItem>, HasKeyHandlers<PillItem>, HasFocusHandlers<PillItem>
	{
		private HTML<AnchorElement>	link;

		private ToggleButton		toggle;

		public PillItem()
		{
			super(ElementResolver.li());
		}
		
		public PillItem icon(Icon icon)
		{
			if(link != null) Widgets.setIcon(this.link, icon);
			if(toggle != null) Widgets.setIcon(toggle, icon);
			return this;
		}
		
		@Override
		public PillItem onFocus(FocusHandler handler)
		{
			if(link != null) link.onFocus(handler);
			if(toggle != null) toggle.onFocus(handler);
			return this;
		}
		
		@Override
		public PillItem onBlur(BlurHandler handler)
		{
			if(link != null) link.onBlur(handler);
			if(toggle != null) toggle.onBlur(handler);
			return this;
		}

		@Override
		public PillItem onKeyPress(KeyPressHandler handler)
		{
			if(link != null) link.onKeyPress(handler);
			if(toggle != null) toggle.onKeyPress(handler);
			return this;
		}

		@Override
		public PillItem onKeyDown(KeyDownHandler handler)
		{
			if(link != null) link.onKeyDown(handler);
			if(toggle != null) toggle.onKeyDown(handler);
			return this;
		}

		@Override
		public PillItem onKeyUp(KeyUpHandler handler)
		{
			if(link != null) link.onKeyUp(handler);
			if(toggle != null) toggle.onKeyUp(handler);
			return this;
		}

		@Override
		public PillItem onMouseDown(MouseDownHandler handler)
		{
			if(link != null) link.onMouseDown(handler);
			if(toggle != null) toggle.onMouseDown(handler);
			return this;
		}

		@Override
		public PillItem onMouseMove(MouseMoveHandler handler)
		{
			if(link != null) link.onMouseMove(handler);
			if(toggle != null) toggle.onMouseMove(handler);
			return this;
		}

		@Override
		public PillItem onMouseOut(MouseOutHandler handler)
		{
			if(link != null) link.onMouseOut(handler);
			if(toggle != null) toggle.onMouseOut(handler);
			return this;
		}

		@Override
		public PillItem onMouseOver(MouseOverHandler handler)
		{
			if(link != null) link.onMouseOver(handler);
			if(toggle != null) toggle.onMouseOver(handler);
			return this;
		}

		@Override
		public PillItem onMouseUp(MouseUpHandler handler)
		{
			if(link != null) link.onMouseUp(handler);
			if(toggle != null) toggle.onMouseUp(handler);
			return this;
		}

		@Override
		public PillItem onMouseWheel(MouseWheelHandler handler)
		{
			if(link != null) link.onMouseWheel(handler);
			if(toggle != null) toggle.onMouseWheel(handler);
			return this;
		}

		@Override
		public PillItem onClick(ClickHandler handler)
		{
			if(link != null) link.onClick(handler);
			if(toggle != null) toggle.onClick(handler);
			return this;
		}

		@Override
		public PillItem onDoubleClick(DoubleClickHandler handler)
		{
			if(link != null) link.onDoubleClick(handler);
			if(toggle != null) toggle.onDoubleClick(handler);
			return this;
		}

		public PillItem activate()
		{
			return Widgets.activateClass(this);
		}

		PillItem link()
		{
			return this.link("");
		}
		
		PillItem link(String id)
		{
			this.link = HTML.asAnchor("#" + id).attribute("data-toggle", "tab");

			return this.add(this.link);
		}

		PillItem toggle()
		{
			this.toggle = new ToggleButton();

			return this.add(this.toggle);
		}
		
		@Override
		protected PillItem add(Widget add)
		{
			return super.add(add);
		}

		public PillItem disable()
		{
			return this.className("disabled");
		}

		@Override
		public PillItem text(String text)
		{
			if (link != null)
			{
				this.link.text(text);
			}
			if (toggle != null)
			{
				this.toggle.text(text);
			}

			return this;
		}

		@Override
		public String getText()
		{
			if(this.toggle != null)
			{
				this.toggle.getText();
			}
			
			return this.link.getText();
		}
	}
}