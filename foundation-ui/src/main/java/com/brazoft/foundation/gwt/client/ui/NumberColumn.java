package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.commons.format.api.Format;
import com.brazoft.foundation.gwt.client.ui.api.TextGridColumn;
import com.brazoft.foundation.gwt.client.util.JSArrays;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.jso.JSObject;

public final class NumberColumn<J extends JSObject>
    extends TextGridColumn<NumberColumn<J>, J> {

    private Format<Number> format;

    public NumberColumn(Format<Number> format) {
	super();
	this.format = format;
    }

    public final String toString(J object) {
	return this.format.format(object.getDouble(this.getName()));
    }

    @Override
    protected void doSort(JsArray<J> rows, String name, SortDirection direction) {
	JSArrays.sortNumber(rows, name, direction.direction());
    }
}