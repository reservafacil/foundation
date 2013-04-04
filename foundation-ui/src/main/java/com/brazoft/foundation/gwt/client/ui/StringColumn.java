package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.ui.api.GridColumn;
import com.google.gwt.core.client.JsArray;
import com.brazoft.foundation.gwt.client.jso.JSObject;

public class StringColumn<J extends JSObject> extends GridColumn<StringColumn<J>, J>
{
	public final String toString(J object)
	{
		return object.get(this.getName());
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