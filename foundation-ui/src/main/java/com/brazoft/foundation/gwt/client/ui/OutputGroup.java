package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.ui.api.Group;
import com.google.gwt.user.client.ui.Widget;

public final class OutputGroup
    extends Group<OutputGroup> {

	private final Widget output;

	public OutputGroup(Widget output) {
		this.output = output;
		this.initWidget(output);
	}

	public Widget getOutput() {
		return this.output;
	}
}