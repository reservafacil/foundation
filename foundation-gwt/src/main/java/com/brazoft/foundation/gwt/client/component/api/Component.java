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

package com.brazoft.foundation.gwt.client.component.api;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

public abstract class Component<C extends Component<C>>
    extends Node<C> {

	public Component(Element element) {
		super(element);
	}

	public static class Util {

		public static <W extends Widget> W setId(W widget, String id) {
			Element element = widget.getElement();
			String holderId = htmlHolderId(element.getId());
			element.setId(id);

			Element span = ElementResolver.getChildById(element, holderId);
			if (span != null) {
				holderId = htmlHolderId(element.getId());
				span.setId(holderId);
			}

			return widget;
		}

		public static <W extends Widget> String getId(W widget) {
			return widget.getElement().getId();
		}

		public static String getHTML(Widget widget) {
			return resolveHTMLHolder(widget).getInnerHTML();
		}

		public static <W extends Widget> W setHTML(W widget, String html) {
			resolveHTMLHolder(widget).setInnerHTML(html);
			return widget;
		}

		private static <W extends Widget> Element resolveHTMLHolder(W widget) {
			Element element = widget.getElement();
			String id = htmlHolderId(element.getId());
			String tagName = element.getTagName().toUpperCase();
			
			if (tagName.equals("SPAN")) {
				element.setId(id);
				return element;
			}

			Element span = ElementResolver.getChildById(element, id);
			if (span == null) {
				span = ElementResolver.span();
				element.appendChild(span);
			}
			span.setId(id);

			return span;
		}

		private static String htmlHolderId(String id) {
			return id + "-text";
		}
	}
}