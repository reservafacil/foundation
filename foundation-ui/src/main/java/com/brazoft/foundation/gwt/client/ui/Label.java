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
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;

public class Label extends Bootstrap<Label> implements HasText<Label>
{
	public Label()
	{
		super(ElementResolver.span());
		this.className("label");
	}
	
	public Label success()
	{
		return this.className("label-success");
	}
	
	public Label warning()
	{
		return this.className("label-warning");
	}
	
	public Label important()
	{
		return this.className("label-important");
	}
	
	public Label info()
	{
		return this.className("label-info");
	}
	
	public Label inverse()
	{
		return this.className("label-inverse");
	}

	@Override
	public Label text(String text)
	{
		return Component.Util.setHTML(this, text);
	}

	@Override
	public String getText()
	{
		return Component.Util.getHTML(this);
	}
}
