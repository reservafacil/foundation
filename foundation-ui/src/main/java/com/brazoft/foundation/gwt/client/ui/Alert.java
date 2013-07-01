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
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.Widget;

public final class Alert
    extends Bootstrap<Alert> {

	private Button              dismiss = new Button().className("close").text("×");

	private StyleChooser<Alert> chooser = new StyleChooser<Alert>("alert-warning", "alert-error", "alert-info", "alert-success");

	public Alert() {
		super(ElementResolver.div());
		this.init();
	}

	private void init() {
		this.className("alert fade in");

		this.dismiss.onClick(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Alert.this.hidden();
			}
		});
	}

	public Alert warning() {
		return this.chooser.className(this, "alert-warning");
	}

	public Alert error() {
		return this.chooser.className(this, "alert-error");
	}

	public Alert info() {
		return this.chooser.className(this, "alert-info");
	}

	public Alert success() {
		return this.chooser.className(this, "alert-success");
	}

	public Alert hidden() {
		this.removeClassName("in").className("out");
		return super.hidden();
	}

	@Override
	public Alert visible() {
		this.removeClassName("out").className("in");
		return super.visible();
	}

	public Alert block() {
		return this.className("alert-block");
	}

	public Alert closeable() {
		return this.add(dismiss);
	}

	@Override
	public Alert add(Widget add) {
		return super.add(add);
	}
}