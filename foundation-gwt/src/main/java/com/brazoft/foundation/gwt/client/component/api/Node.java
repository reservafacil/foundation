package com.brazoft.foundation.gwt.client.component.api;

import com.brazoft.foundation.gwt.client.component.Panel;
import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public class Node<N extends Node<N>>
    extends Composite<N> {

	private Panel panel;

	public Node(Element element) {
		this.panel = new Panel(element);
		super.initWidget(this.panel);
		element.setId(Document.get().createUniqueId());
	}

	public N detachChildren() {
		for (Widget child : this.getChildren()) {
			child.removeFromParent();
		}

		this.panel.clear();
		return (N)this;
	}

	public N remove(Widget child) {
		this.panel.remove(child);
		return (N)this;
	}

	public N add(Widget add) {
		return this.add(add, true);
	}

	public N add(Widget add, boolean ignoreIfParent) {
		if (ignoreIfParent && add.getParent() != null) {
			return (N)this;
		}

		this.panel.add(add);

		return (N)this;
	}

	public N insert(Widget add, Widget before) {
		this.panel.insert(add, before);

		return (N)this;
	}

	public Iterable<Widget> getChildren() {
		return this.panel.children();
	}

	public Widget getChild(int index) {
		return this.panel.getWidget(index);
	}

	public int getIndex(Widget child) {
		return this.panel.getWidgetIndex(child);
	}

	public int childrenCount() {
		return this.panel.getWidgetCount();
	}

	public boolean hasChildren() {
		return this.panel.getWidgetCount() > 0;
	}

	@Override
	protected Panel getWidget() {
		return this.panel;
	}
}