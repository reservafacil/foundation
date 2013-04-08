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

import com.brazoft.foundation.gwt.client.ui.api.DecoratedTextBox;


public class ExtendedTextBox extends DecoratedTextBox<ExtendedTextBox, String>
{
	public ExtendedTextBox prepend(String text)
	{
		return this.add(text, Decoration.PREPENDED);
	}

	public ExtendedTextBox append(String text)
	{
		return this.add(text, Decoration.APPENDED);
	}
	
	public ExtendedTextBox prepend(Icon icon)
	{
		return this.add(icon, Decoration.PREPENDED);
	}

	public ExtendedTextBox append(Icon icon)
	{
		return this.add(icon, Decoration.APPENDED);
	}
	
	public ExtendedTextBox prepend(Button button)
	{
		return this.add(button, Decoration.PREPENDED);
	}

	public ExtendedTextBox append(Button button)
	{
		return this.add(button, Decoration.APPENDED);
	}
	
	public ExtendedTextBox prepend(SplitButton button)
	{
		return this.add(button, Decoration.PREPENDED);
	} 

	public ExtendedTextBox append(SplitButton button)
	{
		return this.add(button, Decoration.APPENDED);
	}

	@Override
	public String getValue()
	{
		return this.getInput().getValue();
	}
	
	@Override
	public ExtendedTextBox value(String value)
	{
		this.getInput().value(value);
		return this;
	}
}