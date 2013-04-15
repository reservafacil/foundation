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

package com.brazoft.foundation.gwt.client.component.api;

import com.brazoft.foundation.gwt.client.component.Panel;
import com.brazoft.foundation.gwt.client.component.Style;
import com.brazoft.foundation.gwt.client.event.Event;
import com.brazoft.foundation.gwt.client.event.EventBus;
import com.brazoft.foundation.gwt.client.event.api.AttachHandler;
import com.brazoft.foundation.gwt.client.event.api.DetachHandler;
import com.brazoft.foundation.gwt.client.event.api.EventHandler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Control;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public abstract class Composite<C extends Composite<C>> extends Control implements IsWidget
{
	private Style<C> style;
	
	private EventBus eventBus;
	
	private Panel panel;
	
	public Composite()
	{
		super();
	}
	
	public Composite(Widget widget)
	{
		this(widget.getElement());
	}
	
	public Composite(Element element)
	{
		this.panel = new Panel(element);
		this.initWidget(this.panel);
		element.setId(Document.get().createUniqueId());
	}
	
	protected C detachChildren()
	{
		for(Widget child : this.getChildren())
		{
			child.removeFromParent();
		}
		
		this.panel.clear();
		return (C) this;
	}
	
	protected C remove(Widget child)
	{
		this.panel.remove(child);
		return (C) this;
	}

	protected C add(Widget add)
	{
		return this.add(add, true);
	}
	
	protected C add(Widget add, boolean ignoreIfParent)
	{
		if(ignoreIfParent && add.getParent() != null)
		{
			return (C) this;
		}
		
		this.panel.add(add);
		
		return (C) this;
	}
	
	protected C insert(Widget add, Widget before)
	{
		this.panel.insert(add, before);
		
		return (C) this;
	}
	
	protected Iterable<Widget> getChildren()
	{
		return this.panel.children();
	}
	
	protected Widget getChild(int index)
	{
		return this.panel.getWidget(index);
	}
	
	protected int getIndex(Widget child)
	{
		return this.panel.getWidgetIndex(child);
	}
	
	protected int childrenCount()
	{
		return this.panel.getWidgetCount();
	}
	
	public boolean hasChildren()
	{
		return this.panel.getWidgetCount() > 0;
	}
	
	@Override
	protected Panel getWidget()
	{
		return this.panel;
	}
	
	public C onAttach(AttachHandler handler)
	{
		this.addAttachHandler(handler);
		return (C) this;
	}
	
	public C onDetach(DetachHandler handler)
	{
		this.addAttachHandler(handler);
		return (C) this;
	}
		
	public C attribute(String name, String value)
	{
		this.getElement().setAttribute(name, value);
		return (C) this;
	}
	
	public C removeAttribute(String name)
	{
		this.getElement().removeAttribute(name);
		return (C) this;
	}
	
	public String getAttribute(String name)
	{
		return this.getElement().getAttribute(name);
	}
	
	public C className(String className)
	{
		this.addStyleName(className);
		return (C) this;
	}
	
	public C styleName(String styleName)
	{
		this.setStyleName(styleName);
		return (C) this;
	}
	
	public C removeClassName(String className)
	{
		this.removeStyleName(className);
		return (C) this;
	}
	
	public String getId()
	{
		return this.getElement().getId();
	}
	
	public C id(String id)
	{
		this.getElement().setId(id);
		return (C) this;
	}
	
	public C name(String name)
	{
		return this.attribute("name", name);
	}
	
	public Style<C> style()
	{
		if(this.style == null)
		{
			this.style = Style.<C>create((C) this);
		}
		
		return this.style;
	}
	
	public C title(String title)
	{
		this.getElement().setTitle(title);
		return (C) this;
	}
	
	public C visible()
	{
		this.setVisible(true);
		return (C) this;
	}
	
	public C hidden()
	{
		this.setVisible(false);
		return (C) this;
	}
	
	protected C addEvent(String type, EventHandler handler)
	{
		if(this.eventBus == null)
		{
			this.eventBus = new EventBus();
		}
		
		this.eventBus.add(type, handler);
		
		return (C) this;
	}
	
	public C fireEvent(String type)
	{
		return this.fireEvent(type, new Event(this));
	}
	
	public C fireEvent(String type, Event event)
	{
		if(this.eventBus != null)
		{
			this.eventBus.fire(type, event);
		}
		
		return (C) this;
	}
	
	protected EventBus eventBus()
	{
		return eventBus;
	}
}