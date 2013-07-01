package com.brazoft.foundation.gwt.client.component.api;

import com.brazoft.foundation.gwt.client.event.api.*;
import com.google.gwt.user.client.ui.IsWidget;

public interface HasVisibility<T>
    extends IsWidget {

	T visible();

	T hidden();

	T onVisible(EventHandler<Void> handler);

	T onHidden(EventHandler<Void> handler);

	public enum TriggerEvent
	    implements EventType {
		HIDDEN, VISIBLE;
	}
}