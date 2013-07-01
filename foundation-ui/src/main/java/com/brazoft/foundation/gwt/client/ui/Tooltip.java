package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.brazoft.foundation.gwt.client.ui.api.Tip;
import com.google.gwt.dom.client.DivElement;

public class Tooltip
    extends Tip<Tooltip>
    implements HasText<Tooltip> {

	private final HTML<DivElement> inner = HTML.asDiv().className("tooltip-inner");

	public Tooltip() {
		this.className("tooltip").add(HTML.asDiv().className("tooltip-arrow")).add(this.inner);
	}

	@Override
	public String getText() {
		return this.inner.getText();
	}

	@Override
	public Tooltip text(String text) {
		this.inner.text(text);
		return this;
	}
}