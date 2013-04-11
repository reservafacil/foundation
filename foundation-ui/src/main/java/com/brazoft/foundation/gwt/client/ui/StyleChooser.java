package com.brazoft.foundation.gwt.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class StyleChooser<W extends Widget>
{
	private String[] names;
	
	public StyleChooser(String... names)
	{
		this.names = names;
	}
	
	public W className(W widget, String className)
	{
		this.removeAll(widget);
		widget.addStyleName(className);
		
		return widget;
	}
	
	public W removeAll(W widget)
	{
		for(String name : names)
		{
			widget.removeStyleName(name);
		}
		
		return widget;
	}
}
