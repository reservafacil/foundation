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

import com.brazoft.foundation.gwt.client.ui.Button.ButtonOptions;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.google.gwt.dom.client.DivElement;

public class SearchForm extends Bootstrap<SearchForm> implements HasText<SearchForm>
{
	private TextBox search = new TextBox();
	
	private Button button = new Button(ButtonOptions.BUTTON);
	
	public SearchForm()
	{
		this(SearchOptions.DEFAULT);
	}
	
	public SearchForm(SearchOptions option)
	{
		super(ElementResolver.form());
		this.init(option);
	}
	
	@Override
	public String getText()
	{
		return this.button.getText();
	}
	
	public SearchForm text(String text) 
	{
		this.button.text(text);
		return this;
	}
	
	private void init(SearchOptions option)
	{
		if(option.equals(SearchOptions.DEFAULT))
		{
			this.add(search);
			this.add(button);
			return;
		}
		
		HTML<DivElement> div = HTML.asDiv();
		if(option.equals(SearchOptions.APPEND))
		{
			div.className("input-append").add(search).add(button);
			this.add(div);
			return;
		}
		
		div.className("input-prepend").add(button).add(search);
	}
	
	public enum SearchOptions
	{
		APPEND,
		DEFAULT,
		PREPEND;
	}
}