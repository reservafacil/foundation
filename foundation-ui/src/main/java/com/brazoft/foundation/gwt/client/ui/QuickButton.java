package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.brazoft.foundation.gwt.client.event.Events;
import com.brazoft.foundation.gwt.client.ui.api.UIButton;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;

public final class QuickButton
    extends Component<QuickButton>
    implements UIButton<QuickButton> {

    private Paragraph                       text         = new Paragraph();

    private HTML<SpanElement>               notification = HTML.asSpan().className("notification");

    private StyleChooser<HTML<SpanElement>> chooser      = new StyleChooser<HTML<SpanElement>>("btn-primary", "btn-info", "btn-success",
	                                                                                       "btn-warning", "btn-danger", "btn-inverse");

    public QuickButton() {
	super(ElementResolver.a());
	this.className("quick-button");
	this.add(this.text).add(this.notification);
    }

    @Override
    public QuickButton onFocus(FocusHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public QuickButton onBlur(BlurHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public QuickButton onClick(ClickHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public QuickButton onDoubleClick(DoubleClickHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public QuickButton onMouseDown(MouseDownHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public QuickButton onMouseMove(MouseMoveHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public QuickButton onMouseOut(MouseOutHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public QuickButton onMouseOver(MouseOverHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public QuickButton onMouseUp(MouseUpHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public QuickButton onMouseWheel(MouseWheelHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public QuickButton onKeyPress(KeyPressHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public QuickButton onKeyDown(KeyDownHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public QuickButton onKeyUp(KeyUpHandler handler) {
	return Events.on(this, handler);
    }

    @Override
    public QuickButton size(Size size) {
	return Widgets.setSize(this, size);
    }

    @Override
    public QuickButton primary() {
	this.chooser.className(this.notification, "btn-primary");
	return this;
    }

    @Override
    public QuickButton info() {
	this.chooser.className(this.notification, "btn-info");
	return this;
    }

    @Override
    public QuickButton success() {
	this.chooser.className(this.notification, "btn-success");
	return this;
    }

    @Override
    public QuickButton warning() {
	this.chooser.className(this.notification, "btn-warning");
	return this;
    }

    @Override
    public QuickButton danger() {
	this.chooser.className(this.notification, "btn-danger");
	return this;
    }

    @Override
    public QuickButton inverse() {
	this.chooser.className(this.notification, "btn-inverse");
	return this;
    }

    @Override
    public QuickButton link() {
	this.notification.className("btn-link");
	return this;
    }

    @Override
    public QuickButton expanded() {
	this.notification.className("btn-block");
	return this;
    }

    @Override
    public QuickButton icon(Icon icon) {
	Widgets.setIcon(this, icon, true);
	return this;
    }

    public QuickButton notification(String notification) {
	this.notification.text(notification);
	return this;
    }

    @Override
    public QuickButton text(String text) {
	this.text.text(text);
	return this;
    }

    @Override
    public String getText() {
	return this.text.getText();
    }
}