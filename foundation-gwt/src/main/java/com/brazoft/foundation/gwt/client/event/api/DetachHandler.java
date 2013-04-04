package com.brazoft.foundation.gwt.client.event.api;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;

public abstract class DetachHandler implements Handler
{
	@Override
	public final void onAttachOrDetach(AttachEvent event)
	{
		if(!event.isAttached())
		{
			this.onDetach(event);
		}
	}
	
	protected abstract void onDetach(AttachEvent event);
}