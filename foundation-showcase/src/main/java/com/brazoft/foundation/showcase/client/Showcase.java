package com.brazoft.foundation.showcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;

public class Showcase implements EntryPoint {
	@Override
	public void onModuleLoad() {
	    
	}

	public void add(Widget widget) {
		RootPanel.get("main-content").add(widget);
	}

	public void remove(Widget widget) {
		RootPanel.get("main-content").remove(widget);
	}
}
