package com.brazoft.foundation.showcase.client;

import com.brazoft.foundation.gwt.client.util.Entry;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.*;

public class Showcase implements EntryPoint {
	@Override
	public void onModuleLoad() {
		Select select = new Select();
	    select.style().width(100, Unit.PCT);
	    
	    this.add(select);
	}

	public void add(Widget widget) {
		RootPanel.get("main-content").add(widget);
	}

	public void remove(Widget widget) {
		RootPanel.get("main-content").remove(widget);
	}
	
	class Select extends Selection<Select, Entry> {
		
        public Select() {
	        this.item("A").item("B").resetable();
        }

		@Override
        public Select value(Entry value) {
	        return null;
        }

		@Override
        public Entry getValue() {
	        return null;
        }
	}
}