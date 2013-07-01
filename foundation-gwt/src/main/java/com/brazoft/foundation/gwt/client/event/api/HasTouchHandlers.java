package com.brazoft.foundation.gwt.client.event.api;

import com.google.gwt.event.dom.client.*;

public interface HasTouchHandlers<T> {

	T onTouchCancel(TouchCancelHandler handler);

	T onTouchEnd(TouchEndHandler handler);

	T onTouchMove(TouchMoveHandler handler);

	T onTouchStart(TouchStartHandler handler);
}
