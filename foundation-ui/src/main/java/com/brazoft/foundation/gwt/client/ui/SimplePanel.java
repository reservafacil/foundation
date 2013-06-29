package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Clear;
import com.google.gwt.user.client.ui.Widget;

public class SimplePanel
    extends Component<SimplePanel> {

    private final Orientation orientation;

    public SimplePanel(Orientation orientation) {
	super(ElementResolver.div());
	this.className("simple-panel");
	this.orientation = orientation;
    }

    public HTML<DivElement> item(Widget widget) {

	HTML<DivElement> div = this.div().add(widget);
	this.add(div);
	return div;
    }

    private HTML<DivElement> div() {
	HTML<DivElement> div = HTML.asDiv().className("simple-container");;

	if (this.orientation.equals(Orientation.HORIZONTAL)) {
	    div.className("pull-left");
	} else {
	    div.style().clear(Clear.BOTH);
	}

	return div;
    }
}