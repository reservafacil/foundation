package com.brazoft.foundation.gwt.client.util;

import com.google.gwt.jso.JSObject;

public class Entry
    extends JSObject {

	protected Entry() {}

	public static native Entry create()/*-{
		return new Object();
	}-*/;

	public final String getKey() {
		return this.get("key");
	}

	public final Entry key(String key) {
		this.set("key", key);
		return this;
	}

	public final String getValue() {
		return this.get("value");
	}

	public final Entry value(String value) {
		this.set("value", value);
		return this;
	}
}