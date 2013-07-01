package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.ui.api.DecoratedInputText;
import com.google.gwt.event.dom.client.*;

public final class NumberBox
    extends DecoratedInputText<NumberBox> {

	public NumberBox() {
		this.onKeyPress(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				int keyCode = event.getNativeEvent().getKeyCode();
				if (Widgets.controlKeys().contains(keyCode)) {
					return;
				}

				if (!"0123456789".contains(String.valueOf(event.getCharCode()))) {
					event.preventDefault();
				}
			}
		});
	}
}