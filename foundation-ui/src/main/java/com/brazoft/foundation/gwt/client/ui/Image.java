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
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;

public final class Image
    extends Bootstrap<Image> {

	public Image() {
		this(ImageOptions.DEFAULT);
	}

	public Image(ImageOptions option) {
		super(ElementResolver.img());
		this.className(option.className());
	}

	public Image src(ImageResource resource) {
		return this.src(resource.getSafeUri().asString());
	}

	public Image src(String url) {
		this.element().setSrc(url);

		return this;
	}

	private ImageElement element() {
		return this.getElement().cast();
	}

	public enum ImageOptions {
		CIRCLE, DEFAULT, POLAROID, ROUNDED;

		String className() {
			switch (this) {
				case CIRCLE:
					return "img-circle";
				case POLAROID:
					return "img-polaroid";
				case ROUNDED:
					return "img-rounded";
				default:
					break;
			}

			return "img";
		}
	}
}