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

package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.event.Events;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.event.api.HasClickHandlers;
import com.brazoft.foundation.gwt.client.ui.*;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public class AbstractTable<T extends AbstractTable<T>>
    extends Bootstrap<T> {

	private Caption caption;

	private Header  header;

	private Body    body = new Body();

	private Footer  footer;

	public AbstractTable() {
		super(ElementResolver.table());
	}

	public T block() {
		this.style().width(100, Unit.PCT);

		return (T)this;
	}

	@Override
	public T align(Alignment alignment) {
		return this.attribute("align", alignment.name().toLowerCase());
	}

	public T cellpadding(int padding) {
		this.element().setCellPadding(padding);
		return (T)this;
	}

	public T cellspacin(int spacing) {
		this.element().setCellSpacing(spacing);
		return (T)this;
	}

	protected Caption caption() {
		if (this.caption == null) {
			this.caption = new Caption();
			this.add(this.caption);
		}

		return this.caption;
	}

	protected Header header() {
		if (this.header == null) {
			this.header = new Header();
			this.add(this.header);
		}

		return this.header;
	}

	protected Body body() {
		this.add(this.body);
		return this.body;
	}

	protected Footer footer() {
		if (this.footer == null) {
			this.footer = new Footer();
			this.add(this.footer);
		}

		return this.footer;
	}

	protected TableElement element() {
		return this.getElement().cast();
	}

	public static class Caption
	    extends Bootstrap<Caption>
	    implements HasText<Caption> {

		public Caption() {
			super(ElementResolver.caption());
		}

		@Override
		public Caption text(String text) {
			return Component.Util.setHTML(this, text);
		}

		@Override
		public String getText() {
			return Component.Util.getHTML(this);
		}
	}

	public static class Header
	    extends Bootstrap<Header> {

		public Header() {
			super(ElementResolver.thead());
		}

		Row row(int index) {
			return (Row)this.getChild(index);
		}

		public Row row() {
			Row row = new Row(true);

			this.add(row);

			return row;
		}
	}

	public static class Footer
	    extends Bootstrap<Footer> {

		public Footer() {
			super(ElementResolver.tfoot());
		}

		Row row(int index) {
			return (Row)this.getChild(index);
		}

		public Row row() {
			Row row = new Row(true);

			this.add(row);

			return row;
		}
	}

	public static class Body
	    extends Bootstrap<Body> {

		public Body() {
			super(ElementResolver.tbody());
		}

		@Override
		public Body detachChildren() {
			return super.detachChildren();
		}

		@Override
		public Iterable<Widget> getChildren() {
			return super.getChildren();
		}

		public Row row(int index) {
			return (Row)this.getChild(index);
		}

		public Row row() {
			Row row = new Row(false);

			this.add(row);

			return row;
		}
	}

	public static class Row
	    extends Bootstrap<Row>
	    implements HasClickHandlers<Row>, HasMouseHandlers<Row> {

		private boolean head;
		
		private StyleChooser<Row> chooser = new StyleChooser<Row>("warning", "error", "info", "success");

		public Row(boolean head) {
			super(ElementResolver.tr());
			this.head = head;
		}
		
		public Row warning() {
			return this.chooser.className(this, "alert-warning");
		}

		public Row error() {
			return this.chooser.className(this, "alert-error");
		}

		public Row info() {
			return this.chooser.className(this, "alert-info");
		}

		public Row success() {
			return this.chooser.className(this, "alert-success");
		}

		@Override
		public Row onMouseDown(MouseDownHandler handler) {
			return Events.on(this, handler);
		}

		@Override
		public Row onMouseMove(MouseMoveHandler handler) {
			return Events.on(this, handler);
		}

		@Override
		public Row onMouseOut(MouseOutHandler handler) {
			return Events.on(this, handler);
		}

		@Override
		public Row onMouseOver(MouseOverHandler handler) {
			return Events.on(this, handler);
		}

		@Override
		public Row onMouseUp(MouseUpHandler handler) {
			return Events.on(this, handler);
		}

		@Override
		public Row onMouseWheel(MouseWheelHandler handler) {
			return Events.on(this, handler);
		}

		@Override
		public Row onClick(ClickHandler handler) {
			return Events.on(this, handler);
		}

		@Override
		public Row onDoubleClick(DoubleClickHandler handler) {
			return Events.on(this, handler);
		}

		public Cell cell(int index) {
			return (Cell)this.getChild(index);
		}

		public Cell cell() {
			Cell cell = new Cell(this.head);

			this.add(cell);

			return cell;
		}

		public static class Cell
		    extends Bootstrap<Cell>
		    implements UICell<Cell> {

			public Cell(boolean head) {
				super(head ? ElementResolver.th() : ElementResolver.td());
			}

			@Override
			public Cell onClick(ClickHandler handler) {
				return Events.on(this, handler);
			}

			@Override
			public Cell onDoubleClick(DoubleClickHandler handler) {
				return Events.on(this, handler);
			}

			@Override
			public Cell onMouseDown(MouseDownHandler handler) {
				return Events.on(this, handler);
			}

			@Override
			public Cell onMouseMove(MouseMoveHandler handler) {
				return Events.on(this, handler);
			}

			@Override
			public Cell onMouseOut(MouseOutHandler handler) {
				return Events.on(this, handler);
			}

			@Override
			public Cell onMouseOver(MouseOverHandler handler) {
				return Events.on(this, handler);
			}

			@Override
			public Cell onMouseUp(MouseUpHandler handler) {
				return Events.on(this, handler);
			}

			@Override
			public Cell onMouseWheel(MouseWheelHandler handler) {
				return Events.on(this, handler);
			}

			@Override
			public Cell add(Widget add) {
				return super.add(add);
			}

			public Cell colspan(int colspan) {
				this.element().setColSpan(colspan);
				return this;
			}

			public Cell rowspan(int rowspan) {
				this.element().setRowSpan(rowspan);
				return this;
			}

			@Override
			public String getText() {
				return Component.Util.getHTML(this);
			}

			@Override
			public Cell text(String text) {
				return Component.Util.setHTML(this, text);
			}

			public Cell icon(Icon icon) {
				return Widgets.setIcon(this, icon);
			}

			public Cell align(Alignment align) {
				this.element().setAlign(align.toString());
				return this;
			}

			public Cell verticalAlign(VerticalAlignment vAlign) {
				this.element().setVAlign(vAlign.toString());
				return this;
			}

			TableCellElement element() {
				return this.getElement().cast();
			}
		}
	}
}