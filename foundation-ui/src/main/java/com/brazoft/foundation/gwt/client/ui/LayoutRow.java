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
import com.brazoft.foundation.gwt.client.ui.Grid.GridOptions;
import com.brazoft.foundation.gwt.client.ui.Layout.LayoutOptions;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.google.gwt.user.client.ui.Widget;

public class LayoutRow extends Bootstrap<LayoutRow>
{
	private LayoutRow()
	{
		super(ElementResolver.div());
	}
	
	LayoutRow(GridOptions option)
	{
		this();
		this.setStyleName(option.rowClass());
	}
	
	public LayoutRow(LayoutOptions option)
	{
		this();
		this.setStyleName(option.rowClass());
	}

	public Cell cell()
	{
		Cell cell = new Cell();

		this.add(cell);

		return cell;
	}
	
	public class Cell extends Bootstrap<Cell> implements HasText<Cell>
	{
		private int	span;

		private int	offset;

		public Cell()
		{
			this(1);
		}

		public Cell(int span)
		{
			super(ElementResolver.div());
			this.span(span);
		}

		public Cell span(int span)
		{
			this.removeClassName("span" + this.span);
			this.span = span;

			return this.className("span" + span);
		}

		public Cell offset(int offset)
		{
			this.removeClassName("offset" + this.offset);
			this.offset = offset;

			return this.className("offset" + offset);
		}

		public Cell item(Widget add)
		{
			return super.add(add);
		}

		@Override
		public Cell text(String text)
		{
			return Component.Util.setHTML(this, text);
		}

		@Override
		public String getText()
		{
			return Component.Util.getHTML(this);
		}
	}
}
