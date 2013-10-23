package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.ui.Widgets;
import com.brazoft.foundation.gwt.client.ui.api.DecoratedInputText;
import com.google.gwt.event.dom.client.*;

public final class NumberBox
    extends DecoratedInputText<NumberBox> {
	private final Character decimalSimbol;
	KeyPressHandler handler = new KeyPressHandler() {
		
		@Override
		public void onKeyPress(KeyPressEvent event) {
			int keyCode = event.getNativeEvent().getKeyCode();
			String accepted = "0123456789";

			if (NumberBox.this.decimalSimbol != null) {
			    accepted += NumberBox.this.decimalSimbol;
			}

			if (Widgets.controlKeys().contains(keyCode)) {
				return;
			}

			if (!accepted.contains(String.valueOf(event.getCharCode()))) {
				event.preventDefault();
			}

			if (decimalSimbol != null  &&  String.valueOf(event.getCharCode()).equals(String.valueOf(NumberBox.this.decimalSimbol))  &&
			    NumberBox.this.getValue().indexOf(NumberBox.this.decimalSimbol) >= 0) {
			    event.preventDefault();
			}
		}
	};

	public NumberBox() {
		this.decimalSimbol = null;
		this.onKeyPress(handler);
	}

	public NumberBox(char decimalSimbol) {
		this.decimalSimbol = decimalSimbol;
		this.onKeyPress(handler);
	}
}