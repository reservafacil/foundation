package com.brazoft.foundation.gwt.client.ui;

import java.util.Date;

import com.brazoft.foundation.commons.format.api.Format;
import com.brazoft.foundation.gwt.client.ui.api.TextGridColumn;
import com.brazoft.foundation.gwt.client.util.JSArrays;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.jso.JSObject;

public final class DateColumn<J extends JSObject>
    extends TextGridColumn<DateColumn<J>, J> {

    private Format<Date> format;

    public DateColumn(Format<Date> format) {
	super();
	this.format = format;
    }

    public final String toString(J object) {
	return this.format.format(object.getDate(this.getName()));
    }

    @Override
    protected void doSort(JsArray<J> rows, String name, SortDirection direction) {
	JSArrays.sort(rows, name, direction.direction());
    }
}