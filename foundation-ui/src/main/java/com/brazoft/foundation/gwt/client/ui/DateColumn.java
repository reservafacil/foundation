package com.brazoft.foundation.gwt.client.ui;

import java.util.Date;

import com.brazoft.foundation.commons.format.api.Format;
import com.brazoft.foundation.gwt.client.jso.JSObject;
import com.brazoft.foundation.gwt.client.ui.api.GridColumn;
import com.google.gwt.core.client.JsArray;

public class DateColumn<J extends JSObject> extends GridColumn<DateColumn<J>, J>
{
	private Format<Date> format;
	
	public DateColumn(Format<Date> format)
	{
		super();
		this.format = format;
	}

	public final String toString(J object)
	{
		return this.format.format(object.getDate(this.getName()));
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