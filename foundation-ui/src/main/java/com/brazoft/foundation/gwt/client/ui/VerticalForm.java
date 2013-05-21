/**
 * Copyright (C) 2009-2012 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
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
import com.brazoft.foundation.gwt.client.ui.api.Form;
import com.google.gwt.dom.client.FieldSetElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.LegendElement;
import com.google.gwt.user.client.ui.Widget;

public final class VerticalForm
    extends Form<VerticalForm> {

    private HTML<FieldSetElement> fieldSet = HTML.asFieldSet();

    private HTML<LegendElement>   legend   = HTML.asLegend();

    private FormAction            action;

    public VerticalForm() {
	super();
	this.init();
    }

    private void init() {
	this.fieldSet.add(this.legend);
	super.add(fieldSet);
    }

    public VerticalForm legend(String text) {
	this.legend.text(text);

	return this;
    }

    public VerticalForm input(UIInput<? extends Widget, ?> input, String labelText) {
	return this.input(input, labelText, null);
    }

    public VerticalForm input(UIInput<? extends Widget, ?> input, String labelText, String helpText) {
	HTML<LabelElement> label = HTML.asLabel().text(labelText);

	this.add(label);

	if (input instanceof CheckBox) {
	    label.add(input.asWidget()).className("checkbox");
	} else if (input instanceof RadioButton) {
	    label.add(input.asWidget()).className("radio");
	} else {
	    this.add(input.asWidget());
	}

	if (helpText != null) {
	    HelpText help = new HelpText().text(helpText);
	    this.add(help);
	}

	return this;
    }

    public FormAction action() {
	if (this.action == null) {
	    this.action = new FormAction();
	    super.add(this.action);
	}

	return this.action;
    }

    @Override
    protected VerticalForm add(Widget add) {
	this.fieldSet.add(add);
	return this;
    }
}