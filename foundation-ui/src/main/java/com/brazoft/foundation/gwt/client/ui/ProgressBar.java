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
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.google.gwt.dom.client.Style.Unit;

public class ProgressBar extends Bootstrap<ProgressBar>
{
	private ProgressCategory category = new ProgressCategory();
	
	public ProgressBar(ProgressBarOptions option)
	{
		super(ElementResolver.div());
		this.init(option);
	}
	
	public ProgressBar height(double value, Unit unit)
	{
		this.style().height(value, unit);
		this.category.style().height(value, unit);
		return this;
	}
	
	public ProgressBar width(double value, Unit unit)
	{
		this.style().width(value, unit);
		return this;
	}
	
	private void init(ProgressBarOptions option)
	{
		this.className("progress");
		this.add(this.category);
		
		switch (option)
		{
			case ANIMATED:
				this.className("progress-striped active");
				break;
			case STRIPED:
				this.className("progress-striped");
			default:
				break;
		}
	}
	
	public ProgressBar success()
	{
		return this.className("progress-success");
	}
	
	public ProgressBar warning()
	{
		return this.className("progress-warning");
	}
	
	public ProgressBar danger()
	{
		return this.className("progress-danger");
	}
	
	public ProgressBar info()
	{
		return this.className("progress-info");
	}
	
	public ProgressBar total(int total)
	{
		this.category.total(total);
		return this;
	}
	
	public ProgressBar worked(int amount)
	{
		this.category.worked(amount);
		return this;
	}
	
	public ProgressBar show()
	{
		return this.visible();
	}
	
	public ProgressBar hide()
	{
		return this.hidden();
	}
	
	public enum ProgressBarOptions
	{
		BASIC,
		STRIPED,
		ANIMATED;
	}
}