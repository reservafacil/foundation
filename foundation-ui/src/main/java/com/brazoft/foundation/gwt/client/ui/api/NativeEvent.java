package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.event.api.AttachHandler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.ui.Widget;

public abstract class NativeEvent<W extends Widget> extends Bootstrap<W>
{
	public NativeEvent(Element element)
	{
		super(element);
		this.init();
	}
	
	private void init()
	{
		this.onAttach(new AttachHandler()
		{
			@Override
			protected void onAttach(AttachEvent event)
			{
				NativeEvent.this.registerNativeEvent();
			}
		});
	}
	
	void registerNativeEvent()
	{
		if(this.eventBus() == null)
		{
			return;
		}
		
		this.registerNativeEvent(this.eventBus().types());
	}
	
	protected abstract void registerNativeEvent(Iterable<String> types);
}