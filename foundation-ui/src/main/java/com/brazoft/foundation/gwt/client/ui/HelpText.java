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
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.brazoft.foundation.gwt.client.ui.Emphasis.EmphasisOptions;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;

class HelpText extends Bootstrap<HelpText> implements HasText<HelpText>
{
	private Emphasis container = new Emphasis(EmphasisOptions.SMALL);
	
	public HelpText()
	{
		this(HelpOptions.BLOCK);
	}
	
	public HelpText(HelpOptions option)
	{
		super(ElementResolver.div());
		this.className(option.className()).add(this.container);
	}
	
	@Override
	public HelpText text(String text)
	{
		this.container.text(text);
		return this;
	}

	@Override
	public String getText()
	{
		return this.container.getText();
	}

	public enum HelpOptions
	{
		BLOCK,
		INLINE;
		
		String className()
		{
			switch (this)
			{
				case BLOCK:
					return "help-block";
				default:
					break;
			}
			
			return "help-inline";
		}
	}
}
