package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.ui.*;
import com.brazoft.foundation.gwt.client.ui.HelpText.HelpOptions;
import com.google.gwt.dom.client.DivElement;

public final class InputControl
    extends Bootstrap<InputControl> {

    private HTML<DivElement>           controls = HTML.asDiv().className("controls");

    private StyleChooser<InputControl> chooser  = new StyleChooser<InputControl>("warning", "error", "info", "success");

    private HelpText                   message;

    public InputControl() {
	super(ElementResolver.div());
	this.className("control-group").add(this.controls);
    }

    public InputControl input(UIInput<?, ?> input) {
	this.message = new HelpText().hidden();
	this.controls.add(input.asWidget()).add(this.message);

	return this;
    }

    public InputControl message(String text) {
	return this.message(text, HelpOptions.BLOCK);
    }

    public InputControl message(String text, HelpOptions options) {
	this.message.visible();
	this.message.text(text);

	return this;
    }

    public InputControl warning() {
	return this.chooser.className(this, "warning");
    }

    public InputControl error() {
	return this.chooser.className(this, "error");
    }

    public InputControl info() {
	return this.chooser.className(this, "info");
    }

    public InputControl success() {
	return this.chooser.className(this, "success");
    }

    public InputControl reset() {
	this.message.hidden();
	return this.chooser.removeAll(this);
    }
}