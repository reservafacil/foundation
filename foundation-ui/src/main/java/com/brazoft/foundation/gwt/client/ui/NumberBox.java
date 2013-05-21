package com.brazoft.foundation.gwt.client.ui;

import java.util.ArrayList;

import com.brazoft.foundation.gwt.client.ui.api.DecoratedInputText;
import com.google.gwt.event.dom.client.*;

public final class NumberBox
    extends DecoratedInputText<NumberBox> {

    private static final java.util.List<Integer> allowedKeys = new ArrayList<Integer>();

    static {
	allowedKeys.add(KeyCodes.KEY_ALT);
	allowedKeys.add(KeyCodes.KEY_CTRL);
	allowedKeys.add(KeyCodes.KEY_BACKSPACE);
	allowedKeys.add(KeyCodes.KEY_DELETE);
	allowedKeys.add(KeyCodes.KEY_LEFT);
	allowedKeys.add(KeyCodes.KEY_RIGHT);
	allowedKeys.add(KeyCodes.KEY_SHIFT);
	allowedKeys.add(KeyCodes.KEY_TAB);
    }

    public NumberBox() {
	this.onKeyPress(new KeyPressHandler() {

	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		int keyCode = event.getNativeEvent().getKeyCode();
		if (allowedKeys.contains(keyCode)) {
		    return;
		}

		if (!"0123456789".contains(String.valueOf(event.getCharCode()))) {
		    event.preventDefault();
		}
	    }
	});
    }
}