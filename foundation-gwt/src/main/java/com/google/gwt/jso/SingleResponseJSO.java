package com.google.gwt.jso;

import com.brazoft.foundation.commons.ResponseStatus;

public class SingleResponseJSO<J extends JSObject>
    extends JSObject {

    private static final String STATUS = "status";

    protected SingleResponseJSO() {}

    public final native J data() /*-{
	                         return this.data;
	                         }-*/;

    public final ResponseStatus status() {
	return ResponseStatus.valueOf(this.getInt(STATUS));
    }
}