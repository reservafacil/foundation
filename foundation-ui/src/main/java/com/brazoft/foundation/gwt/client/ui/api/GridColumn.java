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

import com.brazoft.foundation.gwt.client.ui.Icon;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Row.Cell;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickHandler;
import com.brazoft.foundation.gwt.client.jso.JSObject;

@SuppressWarnings("unchecked")
public abstract class GridColumn<G extends GridColumn<G, J>, J extends JSObject>
{
	private Cell				cell;

	private HTML<SpanElement>	label	= HTML.asSpan();

	private String				name;
	
	private SortDirection		direction = SortDirection.NONE;
	
	public G cell(Cell cell)
	{
		this.cell = cell;
		this.cell.className("sortable").add(this.label);

		return (G) this;
	}
	
	public G onClick(ClickHandler handler)
	{
		this.cell.onClick(handler);
		return (G) this;
	}

	public G icon(Icon icon)
	{
		this.cell.icon(icon);
		return (G) this;
	}

	public G text(String text)
	{
		this.label.text(text);
		return (G) this;
	}
	
	public String getName()
	{
		return name;
	}
	
	public G name(String name)
	{
		this.name = name;
		
		return (G) this;
	}
	
	public G label(String label)
	{
		this.label.text(label);
		return (G) this;
	}
	
	public G unsort()
	{
		this.cell.removeClassName("sorted").icon(Icon.NONE);
		return (G) this;
	}
	
	public G sort(JsArray<J> rows)
	{
		this.direction = this.direction.reverse();
		
		this.cell.className("sorted").icon(this.direction.icon());
		
		this.doSort(rows, this.name, this.direction.value());
		
		return (G) this;
	}
	
	protected abstract void doSort(JsArray<J> rows, String name, int direction);
	
	enum SortDirection
	{
		NONE
		{
			@Override
			public int value()
			{
				return 0;
			}
			
			@Override
			public SortDirection reverse()
			{
				return ASC;
			}
			
			@Override
			public Icon icon()
			{
				return null;
			}
		},
		DESC
		{
			@Override
			public int value()
			{
				return -1;
			}
			
			@Override
			public SortDirection reverse()
			{
				return ASC;
			}
			
			@Override
			public Icon icon()
			{
				return Icon.CHEVRON_DOWN;
			}
		},
		ASC
		{
			@Override
			public int value()
			{
				return 1;
			}
			
			@Override
			public SortDirection reverse()
			{
				return DESC;
			}
			
			@Override
			public Icon icon()
			{
				return Icon.CHEVRON_UP;
			}
		};
		
		public abstract int value();
		
		public abstract SortDirection reverse();
		
		public abstract Icon icon();
	}

	public abstract String toString(J object);
}