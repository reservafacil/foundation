package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.ui.api.Group;
import com.google.gwt.user.client.ui.Widget;

public class RadioGroup extends Group<RadioGroup, String>
{
	private String		name;

	public RadioGroup(Orientation orientation, String name)
	{
		super(orientation);
		this.name = name;
	}

	public RadioGroup item(String text, String value)
	{
		RadioButton button = new RadioButton(this.name).value(value);

		return this.input(button, text);
	}
	
	@Override
	public RadioGroup clear()
	{
		for (Widget child : getChildren())
		{
			RadioButton radio = this.radio(child);
			radio.clear();
		}
		
		return this;
	}

	public RadioGroup value(String value)
	{
		for (Widget child : getChildren())
		{
			RadioButton radio = this.radio(child);
			radio.checked(radio.getValue().equals(value));
		}

		return this;
	}

	public String getValue()
	{
		for (Widget child : getChildren())
		{
			RadioButton radio = this.radio(child);
			if (radio.isChecked())
			{
				return radio.getValue();
			}
		}

		return null;
	}
	
	private RadioButton radio(Widget child)
	{
		Label label = (Label) child;
		return (RadioButton) label.getInput();
	}
}