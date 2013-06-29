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
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.event.Event;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.ui.api.*;
import com.brazoft.foundation.gwt.client.ui.api.NativeEvent;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.Widget;

public final class Accordion
    extends NativeEvent<Accordion> {

    public Accordion() {
	super(ElementResolver.div());
	this.id(ElementResolver.document().createUniqueId()).className("accordion");
    }

    public Accordion item(String heading, String content) {
	return this.item(HTML.asSpan().text(heading), new Paragraph().text(content));
    }

    public Accordion item(Widget heading, String content) {
	return this.item(heading, new Paragraph().text(content));
    }

    public Accordion item(String heading, Widget content) {
	return this.item(HTML.asSpan().text(heading), content);
    }

    public Accordion item(Widget heading, Widget content) {
	this.newItem(heading, content);
	return this;
    }

    public RemovableItem removableItem(String headingText, String actionText, Widget content) {
	RemovableItem heading = new RemovableItem().text(headingText);
	heading.action(actionText, this.newItem(heading, content));

	return heading;
    }

    AccordionItem newItem(Widget heading, Widget content) {
	AccordionItem item = new AccordionItem(this.hasChildren());

	item.heading.item(heading);
	item.collapse.add(content);
	this.add(item);

	return item;
    }

    public int items() {
	return this.getWidget().getWidgetCount();
    }

    public Widget getContentAt(int index) {
	return ((AccordionItem)this.getChild(index)).collapse.getContent();
    }

    public Accordion toogle() {
	this.item(0).toogle();
	return this;
    }

    AccordionItem item(int index) {
	return (AccordionItem)this.getChild(index);
    }

    public class RemovableItem
	extends Component<RemovableItem>
	implements HasText<RemovableItem> {

	private HTML<SpanElement> link   = HTML.asSpan();

	private Button            button = new Button().danger().size(Size.MINI).align(Alignment.RIGHT).icon(Icon.REMOVE_SIGN);

	RemovableItem() {
	    super(ElementResolver.div());
	    this.add(this.link).add(this.button);
	}

	public RemovableItem onRemove(final EventHandler<Void> handler) {
	    ClickHandler clickHandler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent e) {
		    handler.onEvent(new Event<Void>(Type.REMOVE, RemovableItem.this));
		}
	    };

	    this.button.onClick(clickHandler);

	    return this;
	}

	public String getText() {
	    return this.link.getText();
	}

	public RemovableItem text(String text) {
	    this.link.text(text);
	    return this;
	}

	public RemovableItem action(String text, final AccordionItem reference) {
	    this.button.text(text);
	    this.button.onClick(new ClickHandler() {

		@Override
		public void onClick(ClickEvent e) {
		    reference.removeFromParent();
		}
	    });

	    return this;
	}
    }

    public class AccordionItem
	extends Bootstrap<AccordionItem> {

	private AccordionHeading  heading  = new AccordionHeading();

	private AccordionCollapse collapse = new AccordionCollapse();

	public AccordionItem(boolean collapsed) {
	    super(ElementResolver.div());
	    this.className("accordion-group");
	    this.add(this.heading).add(this.collapse).heading.referenceId(this.collapse.getId());

	    if (collapsed) {
		this.collapse.removeClassName("in");
		this.heading.toogle.className("collapsed");
	    }
	}

	public AccordionItem toogle() {
	    this.doToogle(this.getId());
	    return this;
	}

	private native void doToogle(String id)/*-{
	                                       $wnd.$("#" + id).collapse('toogle');
	                                       }-*/;
    }

    class AccordionCollapse
	extends Bootstrap<AccordionCollapse>
	implements HasText<AccordionCollapse> {

	private HTML<DivElement> inner = HTML.asDiv().className("accordion-inner");

	public AccordionCollapse() {
	    super(ElementResolver.div());
	    this.id(ElementResolver.document().createUniqueId()).className("accordion-body collapse in");
	    super.add(this.inner);
	}

	@Override
	public String getText() {
	    return Component.Util.getHTML(this.inner);
	}

	@Override
	public AccordionCollapse text(String text) {
	    Component.Util.setHTML(this.inner, text);
	    return this;
	}

	@Override
	public AccordionCollapse add(Widget add) {
	    this.inner.add(add);
	    return this;
	}

	protected Widget getContent() {
	    return this.inner.getChild(0);
	}
    }

    class AccordionHeading
	extends Bootstrap<AccordionHeading>
	implements HasText<AccordionHeading> {

	private HTML<AnchorElement> toogle = HTML.asAnchor("#").className("accordion-toggle").attribute("data-toggle", "collapse");

	public AccordionHeading() {
	    super(ElementResolver.div());
	    this.className("accordion-heading");
	    super.add(this.toogle);
	    this.toogle.attribute("data-parent", "#" + Accordion.this.getId());
	}

	protected AccordionHeading item(Widget child) {
	    this.toogle.add(child);
	    return this;
	}

	AccordionHeading referenceId(String collapseId) {
	    this.toogle.element().setHref("#" + collapseId);
	    return this;
	}

	@Override
	public String getText() {
	    return Component.Util.getHTML(this.toogle);
	}

	@Override
	public AccordionHeading text(String text) {
	    Component.Util.setHTML(this.toogle, text);
	    return this;
	}
    }

    public Accordion onShow(EventHandler<Void> event) {
	return this.addHandler(Type.SHOW, event);
    }

    public Accordion whenShown(EventHandler<Void> event) {
	return this.addHandler(Type.SHOWN, event);
    }

    public Accordion onHide(EventHandler<Void> event) {
	return this.addHandler(Type.HIDE, event);
    }

    public Accordion whenHidden(EventHandler<Void> event) {
	return this.addHandler(Type.HIDDEN, event);
    }

    enum Type
	implements EventType {
	HIDE, HIDDEN, REMOVE, SHOW, SHOWN, TOGGLE;
    }
}