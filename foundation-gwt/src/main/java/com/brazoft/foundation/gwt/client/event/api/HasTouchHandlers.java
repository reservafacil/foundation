package com.brazoft.foundation.gwt.client.event.api;

import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.user.client.ui.Widget;

public interface HasTouchHandlers<W extends Widget>
{
	W onTouchCancel(TouchCancelHandler handler);
	
	W onTouchEnd(TouchEndHandler handler);
	
	W onTouchMove(TouchMoveHandler handler);
	
	W onTouchStart(TouchStartHandler handler);
}
