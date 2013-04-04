package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.ResponsiveComponent;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.ui.HelpText.HelpOptions;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.google.gwt.dom.client.DivElement;

public class InputGroup extends Bootstrap<InputGroup>
{
	private HTML<DivElement> controls = HTML.asDiv().className("controls");
	
	private FormLabel label;
	
	private HelpText message;
	
	public InputGroup()
	{
		super(ElementResolver.div());
		this.className("control-group").add(this.controls);
	}
	
	public InputGroup label(String text)
	{
		if(this.label == null)
		{
			this.label = new FormLabel();
			this.add(this.label);
		}
		
		this.label.text(text);
		return this;
	}
	
	public InputGroup input(UIInput<?, ?> input)
	{
		if(this.label != null)
		{
			this.label.forId(input.getId());
		}
		
		if(input instanceof ResponsiveComponent)
		{
			((ResponsiveComponent<?>) input).responsiveTo(this.controls);
		}
		
		this.message = new HelpText().text("&nbsp;");
		this.controls.add(input.asWidget()).add(this.message);
		
		return this;
	}
	
	public InputGroup message(String text)
	{
		return this.message(text, HelpOptions.BLOCK);
	}
	
	public InputGroup message(String text, HelpOptions options)
	{
		this.message.text(text);
		
		return this;
	}
	
	public InputGroup reset()
	{
		this.message.text("&nbsp;");
		return this.removeClassName("warning").removeClassName("error").removeClassName("info").removeClassName("success");
	}
	
	public InputGroup warning()
	{
		return this.className("warning");
	}
	
	public InputGroup error()
	{
		return this.className("error");
	}
	
	public InputGroup info()
	{
		return this.className("info");
	}
	
	public InputGroup success()
	{
		return this.className("success");
	}
}