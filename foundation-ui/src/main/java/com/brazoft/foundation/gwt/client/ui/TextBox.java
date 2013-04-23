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
import com.brazoft.foundation.gwt.client.json.JSON;
import com.brazoft.foundation.gwt.client.json.JSONCollection;
import com.brazoft.foundation.gwt.client.json.JSONObject;
import com.brazoft.foundation.gwt.client.ui.api.Input;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.DomEvent;

public class TextBox extends Input<TextBox, String>
{
	private JSONObject options = JSON.asObject();
	
	public TextBox()
	{
		super(ElementResolver.text());
	}
	
	@Override
	public TextBox clear()
	{
		return this.value("");
	}
	
	@Override
	public TextBox value(String value)
	{
		this.element().setValue(value);
		
		DomEvent.fireNativeEvent(Document.get().createChangeEvent(), this);
		
		return this;
	}
	
	/**
	 * @param pattern
	 * 
	 * Character	Description
	 * 9			Number
	 * a			Letter
	 * ?			Alphanumeric
	 * *			Any character
	 * 
	 * @return TextBox instance
	 */
	public TextBox mask(String pattern)
	{
		return this.mask("_", pattern);
	}
	
	public TextBox unmask()
	{
		if(this.isAttached())
		{
			this.nativeUnmask(this.getId());
		}
		return this;
	}
	
	public TextBox mask(String placeholder, String pattern)
	{
		this.options.put("mask", pattern);
		this.options.put("placeholder", placeholder);
		if(this.isAttached())
		{
			this.nativeMask(this.getId(), this.options.getJavaScriptObject());
		}
		else
		{
			this.attribute("data-mask", this.options.get("mask"));
			this.attribute("data-placeholder", this.options.get("placeholder"));
		}
		
		return this;
	}
	
	public TextBox typeahead(JSONCollection<?> values)
	{
		return this.attribute("data-source", values.toString()).attribute("data-provide", "typeahead");
	}
	
	public TextBox typeahead(JSONCollection<?> values, int showItems)
	{
		return this.attribute("data-source", values.toString()).attribute("data-provide", "typeahead").attribute("data-items", String.valueOf(showItems));
	}
	
	public TextBox block()
	{
		return this.className("input-block-level");
	}
	
	@Override
	public String getValue()
	{
		return this.element().getValue();
	}
	
	public TextBox maxLength(int maxLength)
	{
		this.element().setMaxLength(maxLength);
		return this;
	}
	
	private native void nativeMask(String id, JavaScriptObject options)/*-{
		$wnd.$("#" + id).inputmask(options);
	}-*/;
	
	private native void nativeUnmask(String id)/*-{
		$wnd.$("#" + id).inputmask().unmask();
	}-*/;
}