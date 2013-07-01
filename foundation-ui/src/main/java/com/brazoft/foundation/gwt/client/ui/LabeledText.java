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
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;

public final class LabeledText
    extends Bootstrap<LabeledText>
    implements HasText<LabeledText> {

	private StyleChooser<LabeledText> chooser = new StyleChooser<LabeledText>("label-success", "label-warning", "label-important",
	                                                                          "label-info", "label-inverse");

	public LabeledText() {
		super(ElementResolver.span());
		this.className("label");
	}

	public LabeledText success() {
		return this.chooser.className(this, "label-success");
	}

	public LabeledText warning() {
		return this.chooser.className(this, "label-warning");
	}

	public LabeledText important() {
		return this.chooser.className(this, "label-important");
	}

	public LabeledText info() {
		return this.chooser.className(this, "label-info");
	}

	public LabeledText inverse() {
		return this.chooser.className(this, "label-inverse");
	}

	@Override
	public LabeledText text(String text) {
		return Component.Util.setHTML(this, text);
	}

	@Override
	public String getText() {
		return Component.Util.getHTML(this);
	}
}
