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

import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.google.gwt.user.client.ui.Widget;

public class Description extends Bootstrap<Description>
{
	public Description()
	{
		super(ElementResolver.dl());
	}
	
	public Description define(String title, String description)
	{
		return this.add(new Title(title)).add(new Definition(description));
	}
	
	public Description horizontal()
	{
		return this.className("dl-horizontal");
	}
	
	public Description vertical()
	{
		return this.removeClassName("dl-horizontal");
	}
	
	static class Title extends Widget
	{
		public Title(String text)
		{
			this.setElement(ElementResolver.dt());
			Component.Util.setHTML(this, text);
		}
	}
	
	static class Definition extends Widget
	{
		public Definition(String text)
		{
			this.setElement(ElementResolver.dd());
			Component.Util.setHTML(this, text);
		}
	}
}