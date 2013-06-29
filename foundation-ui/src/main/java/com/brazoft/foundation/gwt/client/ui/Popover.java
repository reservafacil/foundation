package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.event.api.HasMouseHandlers;
import com.brazoft.foundation.gwt.client.ui.api.Tip;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.user.client.ui.Widget;

public class Popover
    extends Tip<Popover>
    implements HasMouseHandlers<Popover> {

    private Heading          title   = new Heading(3).className("popover-title");

    private HTML<DivElement> content = HTML.asDiv().className("popover-content");

    public Popover() {
	this.className("popover").add(HTML.asDiv().className("arrow")).add(this.title).add(this.content);
    }

    public Popover content(Widget content) {
	this.content.add(content);
	return this;
    }

    @Override
    public Popover title(String title) {
	this.title.text(title);
	return this;
    }
}