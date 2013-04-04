package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public class RadioGroup extends Bootstrap<RadioGroup> implements UIInput<RadioGroup, String>
{
	private Orientation	orientation;

	private String		name;

	private InputState	contentState	= InputState.NONE;

	//private InputState	valueState		= InputState.NONE;
	
	private boolean required;

	public RadioGroup(Orientation orientation, String name)
	{
		super(ElementResolver.div());
		this.init(orientation, name);
	}

	private void init(Orientation orientation, String name)
	{
		this.orientation = orientation;
		this.name = name;
	}

	public RadioGroup item(String text)
	{
		return this.item(text, text);
	}

	public RadioGroup item(String text, String value)
	{
		RadioButton button = new RadioButton(this.name).attribute("data-value", value);
		this.contentState.visit(button);

		return this.add(this.label(text, button));
	}

	public RadioGroup value(String value)
	{
		for (Widget child : getChildren())
		{
			HTML<LabelElement> label = (HTML<LabelElement>) child;
			RadioButton button = (RadioButton) label.getChild(0);

			String data = button.getAttribute("data-value");
			button.value(data.equals(value));
		}

		return this;
	}

	public String getValue()
	{
		for (Widget child : getChildren())
		{
			HTML<LabelElement> label = (HTML<LabelElement>) child;
			RadioButton button = (RadioButton) label.getChild(0);

			if (button.getValue())
			{
				return button.getAttribute("data-value");
			}
		}

		return null;
	}

	private HTML<LabelElement> label(String text, RadioButton button)
	{
		HTML<LabelElement> label = HTML.asLabel().className("radio").text(text);

		if (this.orientation == Orientation.HORIZONTAL)
		{
			label.className("inline");
		}
		
		label.add(button);
		label.element().setHtmlFor(button.getId());

		return label;
	}

	@Override
	public RadioGroup placeholder(String placeholder)
	{
		return this;
	}
	
	@Override
	public boolean isReadOnly()
	{
		return this.contentState == InputState.READONLY;
	}

	@Override
	public RadioGroup readonly()
	{
		this.contentState = InputState.READONLY;
		return this.process(this.contentState);
	}
	
	@Override
	public boolean isEditable()
	{
		return this.contentState == InputState.EDITABLE;
	}

	@Override
	public RadioGroup editable()
	{
		this.contentState = InputState.EDITABLE;
		return this.process(this.contentState);
	}
	
	@Override
	public boolean isNullable()
	{
		return !this.required;
	}

	@Override
	public RadioGroup nullable()
	{
		this.required = false;
		return this;
	}
	
	@Override
	public boolean isRequired()
	{
		return this.required;
	}

	@Override
	public RadioGroup required()
	{
		this.required = true;
		return this;
	}

	RadioGroup process(InputState state)
	{
		for (Widget child : getChildren())
		{
			HTML<LabelElement> label = (HTML<LabelElement>) child;
			RadioButton button = (RadioButton) label.getChild(0);

			state.visit(button);
		}

		return this;
	}
}