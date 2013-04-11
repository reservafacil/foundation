package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.json.JSONCollection;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;

public class NumberBox extends TextBox
{
	public NumberBox()
	{
		this.onKeyPress(new KeyPressHandler()
		{
			@Override
			public void onKeyPress(KeyPressEvent event)
			{
				if (!"0123456789".contains(String.valueOf(event.getCharCode()))) 
				{
					event.preventDefault();
				}
			}
		});
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