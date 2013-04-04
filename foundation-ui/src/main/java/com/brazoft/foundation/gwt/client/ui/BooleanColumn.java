package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.commons.format.api.Format;
import com.brazoft.foundation.gwt.client.jso.JSObject;
import com.brazoft.foundation.gwt.client.ui.api.GridColumn;
import com.google.gwt.core.client.JsArray;

public class BooleanColumn<J extends JSObject> extends GridColumn<BooleanColumn<J>, J>
{
	private Format<Boolean> format;
	
	public BooleanColumn()
	{
		this(new Format<Boolean>()
		{
			@Override
			public Boolean unformat(String value)
			{
				return Boolean.valueOf(value);
			}
			
			@Override
			public String pattern()
			{
				return "true|false";
			}
			
			@Override
			public String format(Boolean value)
			{
				return String.valueOf(value);
			}
		});
	}
	
	public BooleanColumn(Format<Boolean> format)
	{
		this.format = format;
	}

	public String toString(J object)
	{
		return this.format.format(object.getBoolean(this.getName()));
	}
	
	@Override
	protected native void doSort(JsArray<J> rows, String name, int direction)/*-{
		rows.sort(function(a, b)
		{
			if(a[name] && b[name] || !a[name] && !b[name])
			{ 
				return 0; 
			}
			
			if(direction > 0)
			{
				if(!a[name] && b[name])
				{ 
					return -1;
				} 
				
				return 1; 
			}
			
			if(!b[name] && a[name])
			{ 
				return -1;
			}
			
			return 1;
		});
	}-*/;
}