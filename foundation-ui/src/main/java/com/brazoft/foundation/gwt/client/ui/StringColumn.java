package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.commons.format.api.Format;
import com.brazoft.foundation.gwt.client.ui.api.TextGridColumn;
import com.brazoft.foundation.gwt.client.util.JSArrays;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.jso.JSObject;

public final class StringColumn<J extends JSObject>
    extends TextGridColumn<StringColumn<J>, J> {

    private Format<String> format;

    public StringColumn() {
	super();
    }

    public StringColumn(Format<String> format) {
	this.format = format;
    }

    public final String toString(J object) {
	String value = object.get(this.getName());
	if (this.format != null) {
	    value = this.format.format(value);
	}

	return value;
    }

    @Override
    protected void doSort(JsArray<J> rows, String name, SortDirection direction) {
	JSArrays.sort(rows, name, direction.direction());
    }
}