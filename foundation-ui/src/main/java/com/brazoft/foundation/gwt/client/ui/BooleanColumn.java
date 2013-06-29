package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.commons.format.api.Format;
import com.brazoft.foundation.gwt.client.ui.api.TextGridColumn;
import com.brazoft.foundation.gwt.client.util.JSArrays;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.jso.JSObject;

public final class BooleanColumn<J extends JSObject>
    extends TextGridColumn<BooleanColumn<J>, J> {

    private Format<Boolean> format;

    public BooleanColumn() {
	this(new Format<Boolean>() {

	    @Override
	    public Boolean unformat(String value) {
		return Boolean.valueOf(value);
	    }

	    @Override
	    public String pattern() {
		return "true|false";
	    }

	    @Override
	    public String format(Boolean value) {
		return String.valueOf(value);
	    }
	});
    }

    public BooleanColumn(Format<Boolean> format) {
	this.format = format;
	this.sortable();
    }

    public String toString(J object) {
	return this.format.format(object.getBoolean(this.getName()));
    }

    @Override
    protected void doSort(JsArray<J> rows, String name, SortDirection direction) {
	JSArrays.sortBoolean(rows, name, direction.direction());
    }
}