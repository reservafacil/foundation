package com.brazoft.foundation.showcase.client;

import com.brazoft.foundation.gwt.client.ui.TypeAhead;
import com.brazoft.foundation.gwt.client.util.Entry;
import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.*;

public class Showcase implements EntryPoint {
	@Override
	public void onModuleLoad() {
		Select select = new Select();
	    select.style().width(100, Unit.PCT);
	    
	    this.add(new AutoCompleter());
	    this.add(select);
	    
	    ListableTextBox editor = new ListableTextBox();
	    editor.removeText("Remover");
	    this.add(editor);
	}

	public void add(Widget widget) {
		RootPanel.get("main-content").add(widget);
	}

	public void remove(Widget widget) {
		RootPanel.get("main-content").remove(widget);
	}
	
	class AutoCompleter extends TypeAhead<AutoCompleter, Entry> implements com.brazoft.foundation.gwt.client.ui.TypeAhead.ContentProvider {
        public AutoCompleter() {
	        this.provider(this);
        }

		@Override
        public AutoCompleter clear() {
	        return this;
        }

		@Override
        public AutoCompleter value(Entry value) {
	        return this;
        }

		@Override
        public Entry getValue() {
	        return null;
        }
		
		@Override
		public JsArray<Entry> provideContent(String value, int numberOfItems) {
			JsArray<Entry> entries = JsArray.createArray().cast();
			
			for(int idx = 0; idx < numberOfItems; idx++){
				String key = String.valueOf(idx);
				entries.push(Entry.create().key(key).value(key + ": " + value));
			}
			
			return entries;
		}
	}
	
	class Select extends Selection<Select, Entry> {
		
        public Select() {
	        this.item("A").item("B");
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