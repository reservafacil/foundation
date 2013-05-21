package com.brazoft.foundation.gwt.client.ui;

import com.google.gwt.user.client.ui.Widget;

public enum ViewPort {
    DESKTOP {

	@Override
	public void visible(Widget widget) {
	    widget.addStyleName("visible-desktop");
	}

	@Override
	public void hidden(Widget widget) {
	    widget.addStyleName("hidden-desktop");
	}
    },
    PHONE {

	@Override
	public void visible(Widget widget) {
	    widget.addStyleName("visible-phone");
	}

	@Override
	public void hidden(Widget widget) {
	    widget.addStyleName("hidden-phone");
	}
    },
    TABLET {

	@Override
	public void visible(Widget widget) {
	    widget.addStyleName("visible-tablet");
	}

	@Override
	public void hidden(Widget widget) {
	    widget.addStyleName("hidden-tablet");
	}
    };

    public abstract void visible(Widget widget);

    public abstract void hidden(Widget widget);
}