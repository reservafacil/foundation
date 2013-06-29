package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.Composite;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.event.api.HasClickHandlers;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.event.dom.client.*;

public class Iconic
    extends Composite<Iconic>
    implements HasClickHandlers<Iconic>, HasMouseHandlers<Iconic> {

    private HTML<AnchorElement> anchor = HTML.asAnchor();

    public Iconic() {
	this.initWidget(this.anchor);
    }

    public Iconic icon(Icon icon) {
	return Widgets.setIcon(this, icon);
    }

    @Override
    public Iconic onMouseDown(MouseDownHandler handler) {
	this.anchor.onMouseDown(handler);
	return this;
    }

    @Override
    public Iconic onMouseMove(MouseMoveHandler handler) {
	this.anchor.onMouseMove(handler);
	return this;
    }

    @Override
    public Iconic onMouseOut(MouseOutHandler handler) {
	this.anchor.onMouseOut(handler);
	return this;
    }

    @Override
    public Iconic onMouseOver(MouseOverHandler handler) {
	this.anchor.onMouseOver(handler);
	return this;
    }

    @Override
    public Iconic onMouseUp(MouseUpHandler handler) {
	this.anchor.onMouseUp(handler);
	return this;
    }

    @Override
    public Iconic onMouseWheel(MouseWheelHandler handler) {
	this.anchor.onMouseWheel(handler);
	return this;
    }

    @Override
    public Iconic onClick(ClickHandler handler) {
	this.anchor.onClick(handler);
	return this;
    }

    @Override
    public Iconic onDoubleClick(DoubleClickHandler handler) {
	this.anchor.onDoubleClick(handler);
	return this;
    }
}