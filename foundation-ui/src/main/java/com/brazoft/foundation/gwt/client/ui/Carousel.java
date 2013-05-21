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
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.resources.client.ImageResource;

public final class Carousel
    extends Bootstrap<Carousel> {

    private HTML<DivElement>    inner        = HTML.asDiv().className("carousel-inner");

    private HTML<AnchorElement> leftControl  = HTML.asAnchor("#").className("left carousel-control").attribute("data-slide", "prev").text("‹");

    private HTML<AnchorElement> rightControl = HTML.asAnchor("#").className("right carousel-control").attribute("data-slide", "next").text("›");

    public Carousel() {
	super(ElementResolver.div());
	this.id(ElementResolver.document().createUniqueId());
	this.className("carousel slide");
	this.leftControl.element().setHref("#" + this.getId());
	this.rightControl.element().setHref("#" + this.getId());
	this.add(this.inner).add(this.leftControl).add(this.rightControl);
    }

    public Carousel item(ImageResource resource) {
	this.item().src(resource);
	return this;
    }

    public Carousel item(String url) {
	this.item().src(url);
	return this;
    }

    public Carousel item(ImageResource resource, String heading, String description) {
	this.item().src(resource).caption(heading, description);
	return this;
    }

    public Carousel item(String url, String heading, String description) {
	this.item().src(url).caption(heading, description);
	return this;
    }

    Item item() {
	Item item = new Item();

	if (!this.inner.hasChildren()) {
	    Widgets.activateClass(item);
	}

	this.inner.add(item);

	return item;
    }

    class Item
	extends Bootstrap<Item> {

	private Image   image = new Image();

	private Caption caption;

	public Item() {
	    super(ElementResolver.div());
	    this.className("item").add(this.image);
	}

	Item src(ImageResource resource) {
	    this.image.src(resource);
	    return this;
	}

	Item src(String url) {
	    this.image.src(url);

	    return this;
	}

	Item caption(String heading, String description) {
	    if (this.caption == null) {
		this.caption = new Caption();
		this.add(this.caption);
	    }

	    this.caption.heading(heading).description(description);

	    return this;
	}

	class Caption
	    extends Bootstrap<Caption> {

	    private Heading   heading     = new Heading(4);

	    private Paragraph description = new Paragraph();

	    public Caption() {
		super(ElementResolver.div());
		this.add(this.heading).add(this.description).className("carousel-caption");
	    }

	    Caption heading(String text) {
		this.heading.text(text);
		return this;
	    }

	    Caption description(String text) {
		this.description.text(text);
		return this;
	    }
	}
    }
}