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

import com.brazoft.foundation.gwt.client.ui.Button;
import com.brazoft.foundation.gwt.client.ui.Icon;
import com.brazoft.foundation.gwt.client.ui.InputText;
import com.brazoft.foundation.gwt.client.ui.SplitButton;

@SuppressWarnings("unchecked")
public class DecoratedInputText<D extends DecoratedInputText<D>> extends DecoratedInput<D, String>
{
	public DecoratedInputText()
	{
		super(new InputText());
	}
	
	@Override
	public InputText input()
	{
		return (InputText) super.input();
	}
	
	public D block()
	{
		this.input().block();
		return (D) this;
	}
	
	public D prepend(String text)
	{
		return this.add(text, Decoration.PREPENDED);
	}

	public D append(String text)
	{
		return this.add(text, Decoration.APPENDED);
	}
	
	public D prepend(Icon icon)
	{
		return this.add(icon, Decoration.PREPENDED);
	}

	public D append(Icon icon)
	{
		return this.add(icon, Decoration.APPENDED);
	}
	
	public D prepend(Button button)
	{
		return this.add(button, Decoration.PREPENDED);
	}

	public D append(Button button)
	{
		return this.add(button, Decoration.APPENDED);
	}
	
	public D prepend(SplitButton button)
	{
		return this.add(button, Decoration.PREPENDED);
	} 

	public D append(SplitButton button)
	{
		return this.add(button, Decoration.APPENDED);
	}
	
	@Override
	public D clear()
	{
		return this.value("");
	}
	
	public D maxLength(int maxLength)
	{
		this.input().maxLength(maxLength);
		return (D) this;
	}

	@Override
	public String getValue()
	{
		return this.input().getValue();
	}
	
	@Override
	public D value(String value)
	{
		this.input().value(value);
		return (D) this;
	}
}