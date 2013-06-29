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
import com.google.gwt.user.client.ui.Widget;

public final class HeroUnit
    extends Bootstrap<HeroUnit> {

    private Heading heading = new Heading(1);

    public HeroUnit() {
	super(ElementResolver.div());
	this.className("hero-unit");
	this.item(this.heading);
    }

    public HeroUnit heading(String text) {
	this.heading.text(text);

	return this;
    }

    public HeroUnit item(Widget widget) {
	if (widget instanceof Paragraph) {
	    return super.add(widget);
	}

	return super.add(new Paragraph().add(widget));
    }
}
