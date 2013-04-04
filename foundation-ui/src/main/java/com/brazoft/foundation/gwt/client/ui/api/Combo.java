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

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;

public abstract class Combo<W extends Widget> extends Select<W, String>
{
	public Combo()
	{
		super(false);
	}
	
	public W minimumResultsForSearch(int minimum)
	{
		this.getOptions().put("minimumResultsForSearch", minimum);
		return this.update();
	}
	
	public W selectOnBlur(boolean select)
	{
		this.getOptions().put("selectOnBlur", select);
		return this.update();
	}
	
	public W block()
	{
		this.style().width(100, Unit.PCT);
		return (W) this;
	}
	
	public W allowDeselection(boolean allow)
	{
		this.getOptions().put("allow_single_deselect", allow);
		return (W) this;
	}
	
	@Override
	public W value(String value)
	{
		return this.select(value);
	}
	
	@Override
	public String getValue()
	{
		if(this.element().getSelectedIndex() == -1)
		{
			return null;
		}
		
		return this.element().getOptions().getItem(this.element().getSelectedIndex()).getValue();
	}
}