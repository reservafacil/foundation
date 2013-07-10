package com.brazoft.foundation.gwt.client.util;

import com.google.gwt.core.client.*;
import com.google.gwt.jso.*;

public class LightMap
    extends JavaScriptObject {

	protected LightMap() {}

	public static final native LightMap create()/*-{
		return new Object();
	}-*/;

	public final int size() {
		return this.arrayOfKeys().length();
	}

	public final boolean isEmpty() {
		return this.size() == 0;
	}

	public final native boolean containsKey(String key) /*-{
		return this[key] != undefined;
	}-*/;

	public final native boolean containsValue(String value) /*-{
		for ( var key in this) {
			if (this[key] === value) {
				return true;
			}
		}

		return false;
	}-*/;

	public final native void remove(String key) /*-{
		delete this[key];
	}-*/;

	public final native void putAll(LightMap map) /*-{
		for ( var key in map) {
			this[key] = map[key];
		}
	}-*/;

	public final void putAll(Collection<Entry> entries) {
		for (Entry entry : entries) {
			this.put(entry);
		}
	}

	public final void put(Entry entry) {
		this.put(entry.getKey(), entry.getValue());
	}

	public final native void put(String key, String value) /*-{
		this[key] = value;
	}-*/;

	public final native String get(String property) /*-{
		return this[property];
	}-*/;

	public final native LightMap clear() /*-{
		for ( var key in this) {
			delete this[key];
		}

		return this;
	}-*/;

	public final Iterable<String> keys() {
		return JSIterable.from(this.arrayOfKeys());
	}

	private final native JsArrayString arrayOfKeys() /*-{
		var a = new Array();
		for ( var e in this) {
			a.push(e);
		}
		return a;
	}-*/;
}