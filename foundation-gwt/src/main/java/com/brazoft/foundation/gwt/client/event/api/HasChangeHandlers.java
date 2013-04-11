package com.brazoft.foundation.gwt.client.event.api;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Widget;

public interface HasChangeHandlers<W extends Widget>
{
	W onChange(ChangeHandler handler);
}
