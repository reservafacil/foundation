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

package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.ui.Alignment;
import com.brazoft.foundation.gwt.client.ui.Direction;
import com.brazoft.foundation.gwt.client.ui.ViewPort;
import com.brazoft.foundation.gwt.client.ui.Widgets;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public abstract class Bootstrap<W extends Widget> extends Component<W>
{
	public Bootstrap(Element element)
	{
		super(element);
	}
	
	public W affix()
	{
		return this.className("affix");
	}
	
	public W scrollSpy()
	{
		return (W) Widgets.scrollSpy(this);
	}
		
	public W muted()
	{
		return (W) Widgets.muted(this);
	}
	
	public W align(Alignment alignment)
	{
		return this.className(alignment.className());
	}
	
	public W popover(String text, String content, Direction direction)
	{
		this.attribute("rel", "popover");
		this.attribute("data-placement", direction.name().toLowerCase());
		this.attribute("data-content", content);
		
		return this.attribute("data-original-title", text);
	}
	
	public W tooltip(String text, Direction direction)
	{
		this.attribute("rel", "tooltip");
		this.attribute("data-placement", direction.name().toLowerCase());
		
		return this.attribute("data-original-title", text);
	}
	
	public W visibleOn(ViewPort... viewPorts)
	{
		for(ViewPort viewPort : viewPorts)
		{
			viewPort.visible(this);
		}
		
		return (W) this;
	}
	
	public W hiddenOn(ViewPort... viewPorts)
	{
		for(ViewPort viewPort : viewPorts)
		{
			viewPort.hidden(this);
		}
		
		return (W) this;
	}
}