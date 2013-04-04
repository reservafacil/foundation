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

import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.user.client.ui.Widget;

public class HorizontalForm extends Form<HorizontalForm>
{
	private FormAction action;
	
	public HorizontalForm()
	{
		super();
		this.className("form-horizontal");
	}
	
	public HorizontalForm input(UIInput<? extends Widget, ?> input, String labelText)
	{
		return this.input(input, labelText, null);
	}
	
	public HorizontalForm input(UIInput<? extends Widget, ?> input, String labelText, String helpText)
	{
		HTML<DivElement> controlGroup = this.controlGroup();
		HTML<DivElement> controls = this.controls();
		HTML<LabelElement> label = HTML.asLabel().className("control-label").text(labelText);
		
		if(input instanceof CheckBox)
		{
			label.add(input.asWidget()).className("checkbox");
			controls.add(label);
		}
		else if(input instanceof RadioButton)
		{
			label.add(input.asWidget()).className("radio");
			controls.add(label);
		}
		else
		{
			controls.add(input.asWidget());
		}
		
		controlGroup.add(label);
		controlGroup.add(controls);
		
		if(helpText != null)
		{
			HelpText help = new HelpText().text(helpText);
			controls.add(help);
		}
		
		return this.add(controlGroup);
	}
	
	public HorizontalForm button(Button button)
	{
		HTML<DivElement> controlGroup = this.controlGroup();
		HTML<DivElement> controls = this.controls();
		
		return this.add(controlGroup.add(controls.add(button)));
	}
	
	public FormAction action()
	{
		if(this.action == null)
		{
			this.action = new FormAction();
			this.add(this.action);
		}
		
		return this.action;
	}
	
	private HTML<DivElement> controlGroup()
	{
		return HTML.asDiv().className("control-group");
	}
	
	private HTML<DivElement> controls()
	{
		return HTML.asDiv().className("controls");
	}
}