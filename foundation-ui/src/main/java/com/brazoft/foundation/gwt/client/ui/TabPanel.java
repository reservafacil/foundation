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

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.ui.Tab.TabItem;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.user.client.ui.Widget;

public final class TabPanel
    extends Bootstrap<TabPanel> {

	private Tab              tabs    = new Tab();

	private HTML<DivElement> content = HTML.asDiv().className("tab-content");

	public TabPanel() {
		this(Direction.TOP);
	}

	public TabPanel(Direction direction) {
		super(ElementResolver.div());
		this.init(direction);
	}

	private void init(Direction direction) {
		this.className("tabbable");

		switch (direction) {
			case BOTTOM:
				this.className("tabs-below");
				break;
			case LEFT:
				this.className("tabs-left");
				break;
			case RIGHT:
				this.className("tabs-right");
				break;
			default:
				break;
		}

		this.add(this.tabs).add(this.content);
	}

	public TabItem tab(String id, String label, Widget content) {
		boolean active = !this.tabs.hasChildren();

		TabItem item = this.tabs.item(id, label, active);

		HTML<DivElement> tabPanel = HTML.asDiv().id(id).className("tab-pane");
		if (active) {
			Widgets.activateClass(tabPanel);
		}

		tabPanel.add(content);
		this.content.add(tabPanel);

		return item;
	}
}