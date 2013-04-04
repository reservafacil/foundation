package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.commons.format.api.Format;
import com.brazoft.foundation.gwt.client.jso.JSObject;
import com.brazoft.foundation.gwt.client.ui.api.GridColumn;
import com.google.gwt.core.client.JsArray;

public class NumberColumn<J extends JSObject> extends GridColumn<NumberColumn<J>, J>
{
	private Format<Number> format;
	
	public NumberColumn(Format<Number> format)
	{
		super();
		this.format = format;
	}

	public final String toString(J object)
	{
		return this.format.format(object.getDouble(this.getName()));
	}
	
	@Override
	protected native void doSort(JsArray<J> rows, String name, int direction)/*-{
		rows.sort(function(a,b) 
		{ 
			if(direction > 0)
			{
				return a[name] - b[name];
			}
			
			return b[name] - a[name];
		});
	}-*/;
}