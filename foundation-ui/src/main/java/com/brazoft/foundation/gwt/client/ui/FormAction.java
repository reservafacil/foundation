/**
 * Copyright (C) 2009-2012 the original author or authors. See the notice.md file distributed with
 * this work for additional information regarding copyright ownership.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.ui.Button.ButtonOptions;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;

public final class FormAction
    extends Bootstrap<FormAction> {

	private Toolbar bar = new Toolbar();

	FormAction() {
		super(ElementResolver.div());
		this.bar = new Toolbar();
		this.add(this.bar);
	}

	public Button button(String text) {
		return this.button(text, ButtonOptions.BUTTON);
	}

	public Button reset(String text) {
		return this.button(text, ButtonOptions.RESET);
	}

	public Button submit(String text) {
		return this.button(text, ButtonOptions.SUBMIT);
	}

	Button button(String text, ButtonOptions options) {
		ButtonGroup group = new ButtonGroup();

		this.bar.group(group);

		return group.button(text, options);
	}
}