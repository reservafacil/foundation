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

package com.brazoft.foundation.gwt.client.event;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.Widget;

public class Events {
	
	public static <W extends Widget> W on(W widget, BlurHandler handler) {
		widget.addDomHandler(handler, BlurEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, FocusHandler handler) {
		widget.addDomHandler(handler, FocusEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, ChangeHandler handler) {
		widget.addDomHandler(handler, ChangeEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, ClickHandler handler) {
		widget.addDomHandler(handler, ClickEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, DoubleClickHandler handler) {
		widget.addDomHandler(handler, DoubleClickEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, KeyPressHandler handler) {
		widget.addDomHandler(handler, KeyPressEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, KeyDownHandler handler) {
		widget.addDomHandler(handler, KeyDownEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, KeyUpHandler handler) {
		widget.addDomHandler(handler, KeyUpEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, MouseDownHandler handler) {
		widget.addDomHandler(handler, MouseDownEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, MouseMoveHandler handler) {
		widget.addDomHandler(handler, MouseMoveEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, MouseOutHandler handler) {
		widget.addDomHandler(handler, MouseOutEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, MouseOverHandler handler) {
		widget.addDomHandler(handler, MouseOverEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, MouseUpHandler handler) {
		widget.addDomHandler(handler, MouseUpEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, MouseWheelHandler handler) {
		widget.addDomHandler(handler, MouseWheelEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, TouchCancelHandler handler) {
		widget.addDomHandler(handler, TouchCancelEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, TouchEndHandler handler) {
		widget.addDomHandler(handler, TouchEndEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, TouchMoveHandler handler) {
		widget.addDomHandler(handler, TouchMoveEvent.getType());
		return widget;
	}

	public static <W extends Widget> W on(W widget, TouchStartHandler handler) {
		widget.addDomHandler(handler, TouchStartEvent.getType());
		return widget;
	}
}