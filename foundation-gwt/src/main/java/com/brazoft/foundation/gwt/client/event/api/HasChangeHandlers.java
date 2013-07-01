package com.brazoft.foundation.gwt.client.event.api;

import com.google.gwt.event.dom.client.ChangeHandler;

public interface HasChangeHandlers<T> {

	T onChange(ChangeHandler handler);
}