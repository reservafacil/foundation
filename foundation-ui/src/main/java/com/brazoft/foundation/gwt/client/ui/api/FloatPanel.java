package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.event.Events;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.*;

@SuppressWarnings("unchecked")
public abstract class FloatPanel<F extends FloatPanel<F>>
    extends Component<F>
    implements HasMouseHandlers<F> {

	private boolean     hover;

	private boolean     opened;

	private Selector<?> target;

	public FloatPanel(Element element) {
		super(element);
		this.init();
	}

	private void init() {

		this.onMouseOver(new MouseOverHandler() {

			@Override
			public void onMouseOver(MouseOverEvent event) {
				FloatPanel.this.hover = true;
			}
		}).onMouseOut(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {
				FloatPanel.this.hover = false;
			}
		});
	}

	public F onClose(EventHandler<Void> handler) {
		return this.addHandler(FireableEvent.CLOSE, handler);
	}

	public F onOpen(EventHandler<Void> handler) {
		return this.addHandler(FireableEvent.OPEN, handler);
	}

	@Override
	public F onMouseDown(MouseDownHandler handler) {
		return Events.on((F)this, handler);
	}

	@Override
	public F onMouseMove(MouseMoveHandler handler) {
		return Events.on((F)this, handler);
	}

	@Override
	public F onMouseOut(MouseOutHandler handler) {
		return Events.on((F)this, handler);
	}

	@Override
	public F onMouseOver(MouseOverHandler handler) {
		return Events.on((F)this, handler);
	}

	@Override
	public F onMouseUp(MouseUpHandler handler) {
		return Events.on((F)this, handler);
	}

	@Override
	public F onMouseWheel(MouseWheelHandler handler) {
		return Events.on((F)this, handler);
	}

	public F target(Selector<?> target) {
		this.target = target;
		return (F)this;
	}

	public F open() {
		if (!this.hasChildren()) {
			return this.close();
		}

		this.opened = true;

		double left = 0;
		double top = this.target.top() + this.target.position().top() + this.target.scrollTop() + this.target.outerHeight(false);
		this.style().zIndex(10000).position(Position.ABSOLUTE).display(Display.BLOCK).top(top, Unit.PX).left(left, Unit.PX);

		return this.fireEvent(FireableEvent.OPEN);
	}

	public F close() {
		this.opened = false;
		return this.hidden().fireEvent(FireableEvent.CLOSE);
	}
	
	public F toggle() {
		if(this.opened){
			return this.close();
		}
		
		return this.open();
	}

	@Override
	public F visible() {
		this.visible().style().display(Display.BLOCK);
		return (F)this;
	}

	public boolean isHover() {
		return hover;
	}

	public boolean isOpened() {
		return opened;
	}

	enum FireableEvent
	    implements EventType {
		OPEN, CLOSE;
	}
}
