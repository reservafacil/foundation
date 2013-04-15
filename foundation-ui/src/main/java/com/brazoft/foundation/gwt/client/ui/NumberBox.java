package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.json.JSONCollection;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;

public class NumberBox extends TextBox
{
	public NumberBox()
	{
		this.onKeyPress(new KeyPressHandler()
		{
			@Override
			public void onKeyPress(KeyPressEvent event)
			{
				int keyCode = event.getNativeEvent().getKeyCode();
				if(KeyCodes.KEY_BACKSPACE == keyCode || KeyCodes.KEY_DELETE == keyCode ||
						KeyCodes.KEY_LEFT == keyCode || KeyCodes.KEY_RIGHT == keyCode)
				{
					return;
				}
				
				if (!"0123456789".contains(String.valueOf(event.getCharCode()))) 
				{
					event.preventDefault();
				}
			}
		});
	}
	
	@Override
	public void onBrowserEvent(Event event)
	{
		super.onBrowserEvent(event);
		switch (event.getTypeInt()) 
		{
			case Event.ONPASTE:
				Window.alert(this.getValue());
				event.preventDefault();
				break;
		}
	}
	
	@Override
	public NumberBox value(String value)
	{
		return (NumberBox) super.value(value);
	}

	@Override
	public NumberBox mask(String pattern)
	{
		return (NumberBox) super.mask(pattern);
	}

	@Override
	public NumberBox mask(String placeholder, String pattern)
	{
		return (NumberBox) super.mask(placeholder, pattern);
	}

	@Override
	public NumberBox typeahead(JSONCollection<?> values)
	{
		return (NumberBox) super.typeahead(values);
	}

	@Override
	public NumberBox typeahead(JSONCollection<?> values, int showItems)
	{
		return (NumberBox) super.typeahead(values, showItems);
	}

	@Override
	public NumberBox block()
	{
		return (NumberBox) super.block();
	}

	@Override
	public NumberBox maxLength(int maxLength)
	{
		return (NumberBox) super.maxLength(maxLength);
	}
}