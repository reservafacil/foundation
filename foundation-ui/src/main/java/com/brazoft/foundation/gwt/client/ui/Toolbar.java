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
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;

public final class Toolbar
    extends Bootstrap<Toolbar> {

	public Toolbar() {
		super(ElementResolver.div());
		this.className("btn-toolbar");
	}

	public Toolbar button(Button button) {
		return this.add(button);
	}

	public Toolbar group(ButtonGroup group) {
		return this.add(group);
	}

	public Toolbar group(DropButton group) {
		return this.add(group);
	}

	public Toolbar group(SplitButton group) {
		return this.add(group);
	}
}