package com.brazoft.foundation.gwt.client.event;

import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.event.api.HasClickHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.dom.client.DomEvent.Type;
import com.google.gwt.event.shared.*;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;

public class BrowserEventInterceptor
    implements NativePreviewHandler, HasClickHandlers<BrowserEventInterceptor>, HasFocusHandlers<BrowserEventInterceptor>,
    HasKeyHandlers<BrowserEventInterceptor>, HasMouseHandlers<BrowserEventInterceptor>, HasTouchHandlers<BrowserEventInterceptor>,
    HasHandlers {

    private static final BrowserEventInterceptor instance = new BrowserEventInterceptor();

    private HandlerManager                       manager  = new HandlerManager(this);

    public static BrowserEventInterceptor get() {
	return instance;
    }

    private BrowserEventInterceptor() {
	Event.addNativePreviewHandler(this);
    }

    @Override
    public final void onPreviewNativeEvent(NativePreviewEvent event) {
	try {
	    DomEvent.fireNativeEvent(event.getNativeEvent(), this);
	} finally {
	    event.consume();
	}
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
	this.manager.fireEvent(event);
    }

    @Override
    public BrowserEventInterceptor onClick(ClickHandler handler) {
	return this.put(ClickEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onDoubleClick(DoubleClickHandler handler) {
	return this.put(DoubleClickEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onBlur(BlurHandler handler) {
	return this.put(BlurEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onFocus(FocusHandler handler) {
	return this.put(FocusEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onKeyPress(KeyPressHandler handler) {
	return this.put(KeyPressEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onKeyDown(KeyDownHandler handler) {
	return this.put(KeyDownEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onKeyUp(KeyUpHandler handler) {
	return this.put(KeyUpEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onMouseDown(MouseDownHandler handler) {
	return this.put(MouseDownEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onMouseMove(MouseMoveHandler handler) {
	return this.put(MouseMoveEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onMouseOut(MouseOutHandler handler) {
	return this.put(MouseOutEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onMouseOver(MouseOverHandler handler) {
	return this.put(MouseOverEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onMouseUp(MouseUpHandler handler) {
	return this.put(MouseUpEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onMouseWheel(MouseWheelHandler handler) {
	return this.put(MouseWheelEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onTouchCancel(TouchCancelHandler handler) {
	return this.put(TouchCancelEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onTouchEnd(TouchEndHandler handler) {
	return this.put(TouchEndEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onTouchMove(TouchMoveHandler handler) {
	return this.put(TouchMoveEvent.getType(), handler);
    }

    @Override
    public BrowserEventInterceptor onTouchStart(TouchStartHandler handler) {
	return this.put(TouchStartEvent.getType(), handler);
    }

    private <H extends EventHandler> BrowserEventInterceptor put(Type<H> type, H handler) {
	this.manager.addHandler(type, handler);
	return this;
    }
}