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

package com.brazoft.foundation.gwt.client.component;

import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.event.Events;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.event.api.HasClickHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.Widget;

public final class HTML<E extends Element>
    extends Component<HTML<E>>
    implements HasText<HTML<E>>, HasClickHandlers<HTML<E>>, HasMouseHandlers<HTML<E>>, HasKeyHandlers<HTML<E>>, HasFocusHandlers<HTML<E>>,
    HasTouchHandlers<HTML<E>> {

	private E element;

	private HTML(E element) {
		super(element);
		this.element = element;
	}

	@Override
	public HTML<E> id(String id) {
		return super.id(id);
	}

	@Override
	public HTML<E> onClick(ClickHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onDoubleClick(DoubleClickHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onBlur(BlurHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onFocus(FocusHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onKeyDown(KeyDownHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onKeyPress(KeyPressHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onKeyUp(KeyUpHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onMouseDown(MouseDownHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onMouseMove(MouseMoveHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onMouseOut(MouseOutHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onMouseOver(MouseOverHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onMouseUp(MouseUpHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onMouseWheel(MouseWheelHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onTouchCancel(TouchCancelHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onTouchEnd(TouchEndHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onTouchMove(TouchMoveHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public HTML<E> onTouchStart(TouchStartHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public void setParent(Widget parent) {
		super.setParent(parent);
	}

	public E element() {
		return this.element;
	}

	@Override
	public HTML<E> add(Widget add) {
		return super.add(add);
	}

	@Override
	public HTML<E> add(Widget add, boolean ignoreIfParent) {
		return super.add(add, ignoreIfParent);
	}

	@Override
	public HTML<E> detachChildren() {
		return super.detachChildren();
	}

	@Override
	public HTML<E> remove(Widget child) {
		return super.remove(child);
	}

	@Override
	public Iterable<Widget> getChildren() {
		return super.getChildren();
	}

	@Override
	public Widget getChild(int index) {
		return super.getChild(index);
	}

	@Override
	public int getIndex(Widget child) {
		return super.getIndex(child);
	}

	@Override
	public int childrenCount() {
		return super.childrenCount();
	}

	@Override
	protected Panel getWidget() {
		return super.getWidget();
	}

	@Override
	public HTML<E> insert(Widget add, Widget before) {
		return super.insert(add, before);
	}

	@Override
	public HTML<E> text(String text) {
		return Component.Util.setHTML(this, text);
	}

	@Override
	public String getText() {
		return Component.Util.getHTML(this);
	}

	public static <E extends Element> HTML<E> as(E element) {
		return new HTML<E>(element);
	}

	public static HTML<AnchorElement> asAnchor() {
		HTML<AnchorElement> anchor = HTML.as(ElementResolver.a());
		anchor.style().cursor(Cursor.POINTER);
		return anchor;
	}

	public static HTML<AnchorElement> asAnchor(String href) {
		HTML<AnchorElement> link = HTML.as(ElementResolver.a());
		link.element.setHref(href);

		return link;
	}

	public static HTML<DivElement> asDiv() {
		return HTML.as(ElementResolver.div());
	}

	public static HTML<FieldSetElement> asFieldSet() {
		return HTML.as(ElementResolver.fieldset());
	}

	public static HTML<LabelElement> asLabel() {
		return HTML.as(ElementResolver.label());
	}

	public static HTML<LegendElement> asLegend() {
		return HTML.as(ElementResolver.legend());
	}

	public static HTML<LIElement> asListItem() {
		return HTML.as(ElementResolver.li());
	}

	public static HTML<SpanElement> asSpan() {
		return HTML.as(ElementResolver.span());
	}
}