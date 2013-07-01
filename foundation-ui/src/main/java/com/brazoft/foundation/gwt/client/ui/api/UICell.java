package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.Style;
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.ui.*;
import com.google.gwt.user.client.ui.Widget;

public interface UICell<W extends Widget>
    extends HasText<W>, HasClickHandlers<W>, HasMouseHandlers<W> {

	W add(Widget add);

	W colspan(int colspan);

	W rowspan(int rowspan);

	W icon(Icon icon);

	W align(Alignment align);

	W verticalAlign(VerticalAlignment vAlign);

	W visible();

	W hidden();

	Style<W> style();
}