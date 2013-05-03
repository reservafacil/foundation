package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.commons.format.api.Format;
import com.brazoft.foundation.gwt.client.ui.api.*;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.jso.JSObject;

public class StringColumn<J extends JSObject> extends TextGridColumn<StringColumn<J>, J>
{
    	private Format<String> format;
    	
        public StringColumn() {
	    super();
        }
    
        public StringColumn(Format<String> format) {
	    this.format = format;
        }
    
	public final String toString(J object)
	{
	    String value = object.get(this.getName());
	    if(this.format != null)
	    {
		value = this.format.format(value);
	    }
	    
	    return value;
	}
	
	@Override
	protected native void doSort(JsArray<J> rows, String name, int direction)/*-{
		rows.sort(function(a,b) 
		{ 
			if(direction > 0)
			{
				if (a[name] < b[name]) 
				{
					return -1;
				} 
				
				if (a[name] > b[name])
				{ 
					return 1;
				}
				 
				return 0; 
			}
			
			if (b[name] < a[name]) 
			{
				return -1;
			} 
			
			if (b[name] > a[name])
			{ 
				return 1;
			}
			 
			return 0;
		});
	}-*/;
}