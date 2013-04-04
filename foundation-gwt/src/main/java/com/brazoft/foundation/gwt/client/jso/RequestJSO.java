package com.brazoft.foundation.gwt.client.jso;

public class RequestJSO<J extends JSObject> extends JSObject
{
	protected RequestJSO() {}
	
	public static native <J extends JSObject> RequestJSO<J> create()/*-{
		return new Object();
	}-*/;
	
	public final native RequestJSO<J> data(J data)/*-{
		this.data = data;
		return this;
	}-*/;

	public final native RequestJSO<J> start(int start)/*-{
		this.start = start;
		return this;
	}-*/;

	public final native RequestJSO<J> end(int end)/*-{
		this.end = end;
		return this;
	}-*/;
}