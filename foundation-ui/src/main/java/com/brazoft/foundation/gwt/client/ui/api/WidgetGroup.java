package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.event.api.HasChangeHandlers;
import com.brazoft.foundation.gwt.client.ui.Label;
import com.brazoft.foundation.gwt.client.ui.Orientation;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public abstract class WidgetGroup<G extends WidgetGroup<G, V>, V> extends Bootstrap<G> implements UIInput<G, V>, HasChangeHandlers<G>
{
	private Orientation	orientation;

	private InputState	contentState	= InputState.NONE;

	private boolean required;

	public WidgetGroup(Orientation orientation)
	{
		super(ElementResolver.div());
		this.orientation = orientation;
	}
	
	public G onChange(ChangeHandler handler)
	{
		for (Widget child : getChildren())
		{
			Label label = (Label) child;
			UIInput<?, ?> input = label.getInput();
			if(input instanceof HasChangeHandlers)
			{
				((HasChangeHandlers<?>) input).onChange(handler);
			}
		}
		
		return (G) this;
	}

	public G item(String text)
	{
		return this.item(text, text);
	}

	public abstract G item(String text, String value);
	
	@Override
	public G placeholder(String placeholder)
	{
		return (G) this;
	}
	
	@Override
	public boolean isReadOnly()
	{
		return this.contentState == InputState.READONLY;
	}

	@Override
	public G readonly()
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
	public G editable()
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
	public G nullable()
	{
		this.required = false;
		return (G) this;
	}
	
	@Override
	public boolean isRequired()
	{
		return this.required;
	}

	@Override
	public G required()
	{
		this.required = true;
		return (G) this;
	}
	
	protected G input(UIInput<?, ?> input, String labelText)
	{
		this.contentState.visit(input);

		Label label = new Label(this.orientation).forInput(input).text(labelText);
		
		return this.add(label);
	}
	
	G process(InputState state)
	{
		for (Widget child : getChildren())
		{
			Label label = (Label) child;
			state.visit(label.getInput());
		}

		return (G) this;
	}
}