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

import com.brazoft.foundation.gwt.client.ui.api.DecoratedInputText;

public final class TextBox
    extends DecoratedInputText<TextBox> {

	@Override
	public InputText input() {
		return (InputText)super.input();
	}

	@Override
	public TextBox id(String id) {
		this.input().id(id);
		return this;
	}

	public TextBox mask(String pattern) {
		this.input().mask(pattern);

		return this;
	}

	public TextBox mask(String placeholder, String pattern) {
		this.input().mask(pattern, placeholder);
		return this;
	}
}