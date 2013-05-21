package com.brazoft.foundation.gwt.client.ui.api;

import com.google.gwt.jso.JSObject;

public abstract class GridFilter<J extends JSObject> {

    private boolean active;

    public void activate() {
	this.active = true;
    }

    public void deactivate() {
	this.active = false;
    }

    public boolean filter(J row) {
	if (this.active) {
	    return this.doFilter(row);
	}

	return true;
    }

    protected abstract boolean doFilter(J row);
}