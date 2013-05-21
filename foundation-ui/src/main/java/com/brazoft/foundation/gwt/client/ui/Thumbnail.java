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

import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.user.client.ui.Widget;

public final class Thumbnail
    extends Bootstrap<Thumbnail> {

    private HTML<DivElement> thumbnail = HTML.asDiv().className("thumbnail");

    private Caption          caption;

    public Thumbnail(Image image) {
	super(ElementResolver.li());
	this.add(this.thumbnail.add(image));
    }

    public Thumbnail span(int span) {
	return this.className("span" + span);
    }

    public Caption caption() {
	if (this.caption == null) {
	    this.caption = new Caption();
	}

	this.add(this.caption);

	return this.caption;
    }

    public static class Caption
	extends Bootstrap<Caption>
	implements HasText<Caption> {

	private Heading heading = new Heading(3);

	public Caption() {
	    super(ElementResolver.div());
	    this.className("caption");
	    this.add(heading);
	}

	@Override
	public Caption add(Widget add) {
	    if (add instanceof Paragraph) {
		return super.add(add);
	    }

	    return super.add(new Paragraph().add(add));
	}

	@Override
	public String getText() {
	    return Component.Util.getHTML(this.heading);
	}

	@Override
	public Caption text(String text) {
	    this.heading.text(text);
	    return this;
	}
    }
}