package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.google.gwt.user.client.ui.Widget;

public final class LayoutPanel
    extends Bootstrap<LayoutPanel> {

    public LayoutPanel(Orientation orientation) {
	super(ElementResolver.ul());
	this.className(Orientation.HORIZONTAL.equals(orientation) ? "layout-panel-horizontal" : "layout-panel-vertical");
    }

    public Item item(Widget child) {
	Item item = new Item(child);

	this.add(item);

	return item;
    }

    public static class Item
	extends Bootstrap<Item> {

	private Item(Widget child) {
	    super(ElementResolver.li());
	    this.add(child);
	}

	public Item add(Widget add) {
	    return super.add(add);
	}
    }
}