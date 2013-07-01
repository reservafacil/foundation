package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.event.api.HasMouseHandlers;
import com.brazoft.foundation.gwt.client.ui.api.Tip;
import com.google.gwt.dom.client.DivElement;

public class Popover
    extends Tip<Popover>
    implements HasMouseHandlers<Popover> {

	private Heading          title   = new Heading(3).className("popover-title");

	private HTML<DivElement> body = HTML.asDiv().className("popover-content");

	private HTML<DivElement> footer  = HTML.asDiv().className("popover-footer");

	public Popover() {
		this.className("popover").add(HTML.asDiv().className("arrow")).add(this.title).add(this.body).add(this.footer.hidden());
	}
	
	public HTML<DivElement> body(){
		return this.body;
	}

	public HTML<DivElement> footer() {
		return this.footer.visible();
	}

	@Override
	public Popover title(String title) {
		this.title.text(title);
		return this;
	}
}