package com.brazoft.foundation.gwt.client.component.api;

import com.brazoft.foundation.gwt.client.event.api.AttachHandler;
import com.brazoft.foundation.gwt.client.event.api.DetachHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public interface ResponsiveComponent<W extends Widget> extends IsWidget

{
	W onAttach(AttachHandler handler);
	
	W onDetach(DetachHandler handler);
	
	W responsiveTo(Widget container);
	
	W adaptSize(Widget container);
}
