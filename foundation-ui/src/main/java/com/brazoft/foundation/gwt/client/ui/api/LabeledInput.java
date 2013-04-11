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

import com.brazoft.foundation.gwt.client.component.api.Composite;
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.brazoft.foundation.gwt.client.ui.Label;
import com.google.gwt.event.dom.client.ChangeHandler;

@SuppressWarnings("unchecked")
public abstract class LabeledInput<L extends LabeledInput<L>> extends Composite<L> implements UISelection<L, String>, HasText<L>
{
	private final Label label = new Label();
	
	private final UISelection<?, String> input;
	
	protected LabeledInput(UISelection<?, String> input)
	{
		this.input = input;
		this.label.forInput(this.input);
		this.initWidget(this.label);
	}
	
	@Override
	public L id(String id)
	{
		this.label.forId(id);
		this.input.id(id);
		
		return (L) this;
	}
	
	@Override
	public L onChange(ChangeHandler handler)
	{
		this.input.onChange(handler);
		
		return (L) this;
	}
	
	public L checked(Boolean selected)
	{
		this.input.checked(selected);
		return (L) this;
	}
	
	public Boolean isChecked()
	{
		return this.input.isChecked();
	}
	
	public L value(String value)
	{
		this.input.value(value);
		return (L) this;
	}
	
	public String getValue()
	{
		return this.input.getValue();
	}
	
	@Override
	public L placeholder(String placeholder)
	{
		this.input.placeholder(placeholder);
		return (L) this;
	}


	@Override
	public boolean isReadOnly()
	{
		return this.input.isReadOnly();
	}

	@Override
	public L readonly()
	{
		this.input.readonly();
		return (L) this;
	}

	@Override
	public boolean isEditable()
	{
		return this.input.isEditable();
	}

	@Override
	public L editable()
	{
		this.input.editable();
		return (L) this;
	}

	@Override
	public boolean isNullable()
	{
		return this.input.isNullable();
	}

	@Override
	public L nullable()
	{
		this.input.nullable();
		return (L) this;
	}

	@Override
	public boolean isRequired()
	{
		return this.input.isRequired();
	}

	@Override
	public L required()
	{
		this.input.required();
		return (L) this;
	}
	
	@Override
	public L text(String text)
	{
		this.label.text(text);
		return (L) this;
	}
	
	@Override
	public String getText()
	{
		return this.label.getText();
	}
}