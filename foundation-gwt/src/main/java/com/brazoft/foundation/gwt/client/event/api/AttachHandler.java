package com.brazoft.foundation.gwt.client.event.api;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;

public abstract class AttachHandler implements Handler
{
	@Override
	public final void onAttachOrDetach(AttachEvent event)
	{
		if(event.isAttached())
		{
			this.onAttach(event);
		}
	}
	
	protected abstract void onAttach(AttachEvent event);
}