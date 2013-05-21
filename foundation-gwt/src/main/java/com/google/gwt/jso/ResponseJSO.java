package com.google.gwt.jso;

import com.brazoft.foundation.commons.ResponseStatus;
import com.google.gwt.core.client.JsArray;

public class ResponseJSO<J extends JSObject>
    extends JSObject {

    protected ResponseJSO() {}

    public static native <J extends JSObject> ResponseJSO<J> create()/*-{
	                                                             return new Object();
	                                                             }-*/;

    public final native JsArray<J> array() /*-{
	                                   return this.data == undefined ? new Array() : this.data;
	                                   }-*/;

    public final Collection<J> data() {
	return new Collection<J>(this.array());
    }

    public final ResponseStatus status() {
	return ResponseStatus.valueOf(this.getInt("status"));
    }

    public final native int start() /*-{
	                            return this.start;
	                            }-*/;

    public final native int end() /*-{
	                          return this.end;
	                          }-*/;

    public final native int total() /*-{
	                            return this.total;
	                            }-*/;
}