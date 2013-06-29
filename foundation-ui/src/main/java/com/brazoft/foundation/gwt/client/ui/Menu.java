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
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;

public final class Menu
    extends Bootstrap<Menu> {

    private DropItems dropdown = new DropItems(false);

    public Menu() {
	this(MenuOptions.DROPDOWN);
    }

    public Menu(MenuOptions... options) {
	super(ElementResolver.div());
	this.add(this.dropdown);
	this.init(options);
    }

    private void init(MenuOptions... options) {
	for (MenuOptions option : options) {
	    if (option.equals(MenuOptions.STATIC)) {
		this.dropdown.style().display(Display.BLOCK).position(Position.STATIC).marginBottom(5, Unit.PX);
		continue;
	    }
	    if (option.equals(MenuOptions.DROPUP)) {
		this.className("dropup");
		continue;
	    }
	    this.className("dropdown");
	}
    }

    public Menu item(String label) {
	this.dropdown.item(label);

	return this;
    }

    public DropItems subItem(String label) {
	return this.dropdown.subItem(label);
    }

    public enum MenuOptions {
	STATIC, DROPDOWN, DROPUP;
    }
}