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

import com.brazoft.foundation.gwt.client.ui.api.DecoratedInput;

public class PasswordBox extends DecoratedInput<PasswordBox, String>
{
	public PasswordBox()
	{
		super(new InputPassword());
	}
	
	@Override
	public InputPassword input()
	{
		return (InputPassword) super.input();
	}
	
	public PasswordBox block()
	{
		this.input().block();
		return this;
	}
	
	public PasswordBox prepend(String text)
	{
		return this.add(text, Decoration.PREPENDED);
	}

	public PasswordBox append(String text)
	{
		return this.add(text, Decoration.APPENDED);
	}
	
	public PasswordBox prepend(Icon icon)
	{
		return this.add(icon, Decoration.PREPENDED);
	}

	public PasswordBox append(Icon icon)
	{
		return this.add(icon, Decoration.APPENDED);
	}
	
	public PasswordBox prepend(Button button)
	{
		return this.add(button, Decoration.PREPENDED);
	}

	public PasswordBox append(Button button)
	{
		return this.add(button, Decoration.APPENDED);
	}
	
	public PasswordBox prepend(SplitButton button)
	{
		return this.add(button, Decoration.PREPENDED);
	} 

	public PasswordBox append(SplitButton button)
	{
		return this.add(button, Decoration.APPENDED);
	}
	
	@Override
	public PasswordBox clear()
	{
		return this.value("");
	}

	@Override
	public String getValue()
	{
		return this.input().getValue();
	}
	
	@Override
	public PasswordBox value(String value)
	{
		this.input().value(value);
		return this;
	}
}