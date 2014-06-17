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

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.event.api.HasClickHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.ui.SearchForm.SearchOptions;
import com.brazoft.foundation.gwt.client.ui.api.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Image;

public final class NavigationBar
    extends Bootstrap<NavigationBar>
    implements HasText<NavigationBar> {

	private HTML<AnchorElement> brand = HTML.asAnchor("#").className("brand").hidden();

	private HTML<DivElement>    content;

	private ItemsBar            items;

	private NavigationForm      form;

	private SearchForm          search;

	private HTML<DivElement>	itemConteiner;

	public NavigationBar() {
		this(NavigationBarOptions.DEFAULT);
	}

	public NavigationBar(NavigationBarOptions option) {
		super(ElementResolver.div());
		this.init(option);
	}

	private void init(NavigationBarOptions option) {
		this.className("navbar");

		if (option.equals(NavigationBarOptions.RESPONSIVE)) {
			HTML<DivElement> innerNav = HTML.asDiv().className("navbar-inner");
			this.add(innerNav);

			HTML<DivElement> container = HTML.asDiv().className("container");
			innerNav.add(container);

			HTML<AnchorElement> toggle = HTML.asAnchor("#").className("btn btn-navbar");
			toggle.attribute("data-toggle", "collapse").attribute("data-target", ".nav-collapse");
			toggle.add(HTML.asSpan().className("icon-bar")).add(HTML.asSpan().className("icon-bar")).add(HTML.asSpan().className("icon-bar"));

			this.content = HTML.asDiv().className("nav-collapse collapse");

			container.add(toggle).add(this.brand).add(this.content);
			return;
		}

		switch (option) {
			case FIX_AT_TOP:
				this.className("navbar-fixed-top");
				break;
			case FIX_AT_BOTTOM:
				this.className("navbar-fixed-bottom");
				break;
			case STATIC:
				this.className("navbar-static-top");
				break;
			default:
				break;
		}

		this.content = HTML.asDiv().className("navbar-inner");
		this.content.style().paddingRight(0, Unit.PX);
		this.add(this.content);

		this.content.add(this.brand);
		this.add(this.content);
	}

	public NavigationBar inverse() {
		return this.className("navbar-inverse");
	}

	public SearchForm search() {
		if (this.search == null) {
			this.search = new SearchForm(SearchOptions.APPEND);
			this.search.setStyleName("navbar-search");
			// this.search.getInput().className("search-query");
		}
		this.content.add(search);

		return this.search;
	}

	public NavigationForm form() {
		if (this.form == null) {
			this.form = new NavigationForm();
		}

		this.content.add(this.form);

		return this.form;
	}

	public ItemsBar items() {
		if (this.items == null) {
			this.items = new ItemsBar();
		}

		this.content.add(this.items);
		return this.items;
	}

	public void clearContainer() {
		if (this.content != null && this.itemConteiner != null) {
			this.content.remove(this.itemConteiner);
		}
	}

	public HTML<DivElement> container() {
		this.clearContainer();

		this.itemConteiner = HTML.asDiv().className("tab-content");
		this.itemConteiner.addStyleName("navbar-background-ativo");

		this.content.add(this.itemConteiner);

		return this.itemConteiner;
	}
	
	@Override
	public String getText() {
		return Component.Util.getHTML(this.brand);
	}

	@Override
	public NavigationBar text(String text) {
		this.brand.text(text);

		return this;
	}

	public NavigationBar brand(Image image) {
		this.brand.visible().add(image);
		return this;
	}

	public NavigationBar brand(Icon icon) {
		this.brand.visible().add(new Iconic().icon(icon));
		return this;
	}

	public static class NavigationForm
	    extends Form<NavigationForm> {

		public NavigationForm() {
			this.className("navbar-form");
		}

		public NavigationForm input(UIInput<? extends Widget, ?> input, String labelText) {
			return this.add(input.placeholder(labelText).asWidget());
		}
	}

	public static class ItemsBar
	    extends Bootstrap<ItemsBar> {

		public ItemsBar() {
			super(ElementResolver.ul());
			this.className("nav");
		}

		public ItemsBar activate(int index) {
			deactivateAll(this.getWidget().getWidget(index));
			Widgets.activateClass(this.getWidget().getWidget(index));
			this.getWidget().getWidget(index).addStyleName("navbar-background-ativo");
			return this;
		}

		public ItemsBar activate(Widget widget) {
			deactivateAll(widget);
			Widgets.activateClass(widget);
			widget.addStyleName("navbar-background-ativo");
			return this;
		}

		public void deactivateAll(Widget widgetSelection) {
			for (Widget child : this.getChildren()) {
				Widgets.deactivateClass(child);
				child.removeStyleName("navbar-background-ativo");
			}
		}

		public ItemsBar divider() {
			HTML<LIElement> divider = HTML.asListItem().className("divider-vertical");
			return this.add(divider);
		}

		public Item item(String label) {
			return this.item().text(label);
		}

		public Item item(com.brazoft.foundation.gwt.client.ui.Image image) {
			return this.item().image(image);
		}

		public Item item(com.brazoft.foundation.gwt.client.ui.Image image, String text) {
			return this.item().image(image, text);
		}

		public Item item(UIButton<? extends Widget> button) {
			Item item = this.item().add(button);

			if (button instanceof DropButton) {
				item.className("dropdown");
			}

			return item;
		}

		Item item() {
			Item item = new Item();
			this.add(item);

			return item;
		}

		public final class Item
		    extends Bootstrap<Item>
		    implements HasText<Item>, HasFocusHandlers<Item>, HasClickHandlers<Item>, HasMouseHandlers<Item>, HasKeyHandlers<Item> {

			private final HTML<AnchorElement> link = HTML.asAnchor();

			public Item() {
				super(ElementResolver.li());
			}
			
            public HTML<AnchorElement> link() {
	            return link;
            }

			@Override
			public Item onKeyPress(KeyPressHandler handler) {
				this.link.onKeyPress(handler);
				return this;
			}

			@Override
			public Item onKeyDown(KeyDownHandler handler) {
				this.link.onKeyDown(handler);
				return this;
			}

			@Override
			public Item onKeyUp(KeyUpHandler handler) {
				this.link.onKeyUp(handler);
				return this;
			}

			@Override
			public Item onMouseDown(MouseDownHandler handler) {
				this.link.onMouseDown(handler);
				return this;
			}

			@Override
			public Item onMouseMove(MouseMoveHandler handler) {
				this.link.onMouseMove(handler);
				return this;
			}

			@Override
			public Item onMouseOut(MouseOutHandler handler) {
				this.link.onMouseOut(handler);
				return this;
			}

			@Override
			public Item onMouseOver(MouseOverHandler handler) {
				this.link.onMouseOver(handler);
				return this;
			}

			@Override
			public Item onMouseUp(MouseUpHandler handler) {
				this.link.onMouseUp(handler);
				return this;
			}

			@Override
			public Item onMouseWheel(MouseWheelHandler handler) {
				this.link.onMouseWheel(handler);
				return this;
			}

			@Override
			public Item onClick(ClickHandler handler) {
				this.link.onClick(handler);
				return this;
			}

			@Override
			public Item onDoubleClick(DoubleClickHandler handler) {
				this.link.onDoubleClick(handler);
				return this;
			}

			@Override
			public Item onFocus(FocusHandler handler) {
				this.link.onFocus(handler);
				return this;
			}

			@Override
			public Item onBlur(BlurHandler handler) {
				this.link.onBlur(handler);
				return this;
			}

			Item add(UIButton<?> button) {
				return this.add(button.asWidget());
			}

			@Override
			public String getText() {
				return Component.Util.getHTML(this.link);
			}

			public Item image(com.brazoft.foundation.gwt.client.ui.Image image) {
				this.link.add(image);
				return this.add(this.link);
			}

			public Item image(com.brazoft.foundation.gwt.client.ui.Image image, String text) {
				this.link.add(image).add(new Label().text(text));
				return this.add(this.link);
			}

			@Override
			public Item text(String text) {
				this.add(this.link);
				Component.Util.setHTML(this.link, text);

				return this;
			}

			@Override
			public Item focus() {
				this.link.focus();
				return this;
			}

			@Override
			public Item blur() {
				this.link.blur();
				return this;
			}
		}
	}

	public enum NavigationBarOptions {
		DEFAULT, FIX_AT_TOP, FIX_AT_BOTTOM, STATIC, RESPONSIVE;
	}
}