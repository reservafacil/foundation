package com.brazoft.foundation.gwt.client.ui;

import java.util.ArrayList;

import com.brazoft.foundation.gwt.client.ui.api.Group;
import com.google.gwt.user.client.ui.Widget;

public class MultiCheckBox extends Group<MultiCheckBox, String[]>
{
	public MultiCheckBox(Orientation orientation)
	{
		super(orientation);
	}

	@Override
	public MultiCheckBox value(String[] values)
	{
		for (Widget child : getChildren())
		{
			CheckBox radio = this.checkbox(child);
			for(String value : values)
			{
				radio.checked(radio.getValue().equals(value));
			}
		}
		
		return this;
	}

	@Override
	public String[] getValue()
	{
		java.util.List<String> values = new ArrayList<String>();
		
		for (Widget child : getChildren())
		{
			CheckBox radio = this.checkbox(child);
			if(radio.isChecked())
			{
				values.add(radio.getValue());
			}
		}
		
		return values.toArray(new String[values.size()]);
	}

	@Override
	public MultiCheckBox item(String text, String value)
	{
		CheckBox input = new CheckBox().value(value);
		
		return this.input(input, text);
	}
	
	private CheckBox checkbox(Widget child)
	{
		Label label = (Label) child;
		return (CheckBox) label.getInput();
	}
}