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

import java.util.ArrayList;

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.ui.api.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.regexp.shared.*;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public final class Widgets {

	static final StyleChooser<Widget>            btnChooser  = new StyleChooser<Widget>("btn-primary", "btn-info", "btn-success",
	                                                                                    "btn-warning", "btn-danger", "btn-inverse");

	private static final java.util.List<Integer> controlKeys = new ArrayList<Integer>();

	static {
		controlKeys.add(KeyCodes.KEY_ALT);
		controlKeys.add(KeyCodes.KEY_CTRL);
		controlKeys.add(KeyCodes.KEY_BACKSPACE);
		controlKeys.add(KeyCodes.KEY_DELETE);
		controlKeys.add(KeyCodes.KEY_LEFT);
		controlKeys.add(KeyCodes.KEY_RIGHT);
		controlKeys.add(KeyCodes.KEY_SHIFT);
		controlKeys.add(KeyCodes.KEY_TAB);
		controlKeys.add(KeyCodes.KEY_ENTER);
		controlKeys.add(KeyCodes.KEY_UP);
		controlKeys.add(KeyCodes.KEY_DOWN);
		controlKeys.add(KeyCodes.KEY_LEFT);
		controlKeys.add(KeyCodes.KEY_RIGHT);
		controlKeys.add(KeyCodes.KEY_PAGEDOWN);
		controlKeys.add(KeyCodes.KEY_PAGEUP);
		controlKeys.add(KeyCodes.KEY_HOME);
		controlKeys.add(KeyCodes.KEY_END);
		controlKeys.add(KeyCodes.KEY_ESCAPE);
	}

	public static java.util.List<Integer> controlKeys() {
		return controlKeys;
	}

	public static <W extends Widget> W setData(W widget, String value) {
		widget.getElement().setAttribute("data-value", value);
		return widget;
	}

	public static <W extends Widget> String getData(W widget) {
		return widget.getElement().getAttribute("data-value");
	}

	public static <W extends Widget> W muted(W widget) {
		widget.addStyleName("muted");
		return widget;
	}

	public static <W extends Widget> W setPopover(W widget, String text, String content, Direction direction) {
		widget.getElement().setAttribute("rel", "popover");
		widget.getElement().setAttribute("data-placement", direction.name().toLowerCase());
		widget.getElement().setAttribute("data-content", content);
		widget.getElement().setAttribute("data-original-title", text);

		return widget;
	}

	public static <W extends Widget> W setTooltip(W widget, String text, Direction direction) {
		widget.getElement().setAttribute("rel", "tooltip");
		widget.getElement().setAttribute("data-placement", direction.name().toLowerCase());
		widget.getElement().setAttribute("data-original-title", text);

		return widget;
	}

	public static <W extends Widget> W setIcon(W widget, Icon icon) {
		return setIcon(widget, icon, false);
	}

	public static boolean mustBeWhiteIcon(String className) {
		MatchResult matcher = RegExp.compile("primary|info|success|warning|danger|inverse").exec(className);
		return matcher != null && matcher.getGroupCount() > 0;
	}

	public static <W extends Widget> W setIcon(W widget, Icon icon, boolean insert) {
		Element element = widget.getElement();
		String className = element.getClassName();

		boolean white = mustBeWhiteIcon(className);

		Element i = ElementResolver.getElementByTagName(element, "i");
		if (i == null) {
			i = ElementResolver.create("i");

			if (insert) {
				widget.getElement().insertFirst(i);
			} else {
				widget.getElement().appendChild(i);
			}
		}

		i.setClassName(icon.className(white));

		return widget;
	}

	public static <W extends Widget> W scrollSpy(W widget) {
		BodyElement body = ElementResolver.body();
		body.setAttribute("data-spy", "scroll");
		body.setAttribute("data-target", widget.getStyleName());

		return widget;
	}

	static <B extends UIButton<? extends Widget>> B setSize(B button, Size size) {
		switch (size) {
			case LARGE:
				return (B)button.className("btn-large");
			case SMALL:
				return (B)button.className("btn-small");
			case MINI:
				return (B)button.className("btn-mini");
			default:
				break;
		}

		return (B)button;
	}

	static <B extends UIButton<? extends Widget>> B setPrimary(B button) {
		btnChooser.className(button.asWidget(), "btn-primary");
		return button;
	}

	static <B extends UIButton<? extends Widget>> B setInfo(B button) {
		btnChooser.className(button.asWidget(), "btn-info");
		return button;
	}

	static <B extends UIButton<? extends Widget>> B setSuccess(B button) {
		btnChooser.className(button.asWidget(), "btn-success");
		return button;
	}

	static <B extends UIButton<? extends Widget>> B setWarning(B button) {
		btnChooser.className(button.asWidget(), "btn-warning");
		return button;
	}

	static <B extends UIButton<? extends Widget>> B setDanger(B button) {
		btnChooser.className(button.asWidget(), "btn-danger");
		return button;
	}

	static <B extends UIButton<? extends Widget>> B setInverse(B button) {
		btnChooser.className(button.asWidget(), "btn-inverse");
		return button;
	}

	static <B extends UIButton<? extends Widget>> B setLink(B button) {
		return (B)button.className("btn-link");
	}

	static <B extends UIButton<? extends Widget>> B setExpanded(B button) {
		return (B)button.className("btn-block");
	}

	static void adaptPlaceholder(Input<?, ?> input, String placeholder, String className) {
		Widget parent = input.getParent();

		if (!parent.getElement().hasTagName("label")) {
			HTML<LabelElement> label = HTML.asLabel().text(placeholder).className(className);
			DOM.appendChild(parent.getElement(), label.getElement());
			label.setParent(parent);
			label.add(input);
			return;
		}

		parent.getElement().setInnerText(placeholder);
		parent.addStyleName(className);
	}

	static <W extends Widget> W activateClass(W widget) {
		widget.addStyleName("active");

		return widget;
	}

	static <W extends Widget> W deactivateClass(W widget) {
		widget.removeStyleName("active");

		return widget;
	}

	public static boolean isDisabledClass(Widget widget) {
		return widget.getStyleName().contains("disabled");
	}

	public static <W extends Widget> W disabledClass(W widget) {
		widget.addStyleName("disabled");

		return widget;
	}

	public static <W extends Widget> W enabledClass(W widget) {
		widget.removeStyleName("disabled");

		return widget;
	}
}