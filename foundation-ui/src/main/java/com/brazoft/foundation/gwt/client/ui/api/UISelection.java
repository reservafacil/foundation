package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.event.api.HasChangeHandlers;
import com.google.gwt.user.client.ui.Widget;

public interface UISelection<W extends Widget, V>
    extends UIInput<W, V>, HasChangeHandlers<W> {

	W checked(Boolean checked);

	Boolean isChecked();
}
