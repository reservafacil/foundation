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

@SuppressWarnings("unchecked")
public abstract class Combo<C extends Combo<C, V>, V> extends Select<C, V>
{
	public Combo()
	{
		super(false);
	}
	
	public C minimumResultsForSearch(int minimum)
	{
		this.getOptions().put("minimumResultsForSearch", minimum);
		return this.update();
	}
	
	public C selectOnBlur(boolean select)
	{
		this.getOptions().put("selectOnBlur", select);
		return this.update();
	}
	
	public C block()
	{
		this.style().width(100, Unit.PCT);
		return (C) this;
	}
	
	public C allowDeselection(boolean allow)
	{
		this.getOptions().put("allow_single_deselect", allow);
		return (C) this;
	}
}