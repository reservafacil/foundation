package com.google.gwt.jso;


public class SingleResponseJSO<J extends JSObject>
    extends StatusResponseJSO<J> {

	protected SingleResponseJSO() {}

	public final native J data() /*-{
		return this.data;
	}-*/;
}