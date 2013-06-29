package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.event.api.*;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.AttachEvent;

@SuppressWarnings("unchecked")
public abstract class NativeEvent<N extends NativeEvent<N>>
    extends Bootstrap<N> {

    public NativeEvent(Element element) {
	super(element);
	this.init();
    }

    private void init() {
	this.onAttach(new AttachHandler() {

	    @Override
	    protected void onAttach(AttachEvent event) {
		NativeEvent.this.registerNativeEvent();
	    }
	});
    }

    void registerNativeEvent() {
	if (this.eventBus() == null) {
	    return;
	}

	Iterable<EventType> types = this.eventBus().types();
	for (EventType type : types) {
	    this.registerEvent((N)this, this.toMethod(type), type, this.getId());
	}
    }

    protected native void registerEvent(N widget, String method, EventType type, String id) /*-{
	                                                                                    $wnd.$("#" + id).on(method, function () {
	                                                                                    widget.@com.brazoft.foundation.gwt.client.component.api.EventSource::fireEvent(Lcom/brazoft/foundation/gwt/client/event/api/EventType;)(type);
	                                                                                    });
	                                                                                    }-*/;

    protected String toMethod(EventType type) {
	return type.name().toLowerCase();
    }
}