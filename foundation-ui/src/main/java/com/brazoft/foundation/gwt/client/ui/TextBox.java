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

import com.brazoft.foundation.gwt.client.json.JSONCollection;
import com.brazoft.foundation.gwt.client.ui.api.DecoratedInputText;
import com.google.gwt.json.client.JSONArray;

public class TextBox extends DecoratedInputText<TextBox>
{
	@Override
	public InputText input()
	{
		return (InputText) super.input();
	}
	
	public TextBox mask(String pattern)
	{
		this.input().mask(pattern);
		
		return this; 
	}
	
	public TextBox mask(String placeholder, String pattern)
	{
		this.input().mask(pattern, placeholder);
		return this;
	}
	
	public TextBox typeahead(JSONCollection<?> values)
	{
		this.input().typeahead(values);
		return this;
	}
	
	public TextBox typeahead(JSONCollection<?> values, int showItems)
	{
		this.input().typeahead(values, showItems);
		return this;
	}
	
	public TextBox typeahead(JSONArray values)
	{
		this.input().attribute("data-source", values.toString()).attribute("data-provide", "typeahead");
		return this;
	}
	
	public TextBox typeahead(JSONArray values, int showItems)
	{
		this.input().attribute("data-source", values.toString()).attribute("data-provide", "typeahead").attribute("data-items", String.valueOf(showItems));
		return this;
	}
}