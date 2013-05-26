package com.brazoft.foundation.gwt.client.component.api;

import com.brazoft.foundation.gwt.client.event.*;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.*;

@SuppressWarnings("unchecked")
public class EventSource<E extends EventSource<E>>
    extends Control
    implements IsWidget {

    private EventBus eventBus = new EventBus();

    protected <T> E addHandler(EventType type, EventHandler<T> handler) {
	this.eventBus.add(type, handler);
	return (E)this;
    }

    public E fireEvent(EventType type) {
	return this.fireEvent(new Event<Object>(type, this));
    }

    public <T> E fireEvent(Event<T> event) {
	this.eventBus.fire(event);
	return (E)this;
    }

    public E removeHandlers(Event<?> event) {
	this.eventBus.remove(event.type());
	return (E)this;
    }
    
    public <H extends com.google.gwt.event.shared.EventHandler> E removeHandlers(Type<H> type, H handler)
    {
	this.getHandlerManager().removeHandler(type, handler);
	return (E)this;
    }

    public <H extends com.google.gwt.event.shared.EventHandler> E removeHandlers(GwtEvent.Type<H> type) {
	int counter = this.getHandlerManager().getHandlerCount(type);

	for (int index = 0; index < counter; index++) {
	    H handler = this.getHandlerManager().getHandler(type, index);
	    this.getHandlerManager().removeHandler(type, handler);
	}

	return (E)this;
    }

    protected EventBus eventBus() {
	return eventBus;
    }
}