/**
 * Copyright (C) 2009-2012 the original author or authors. See the notice.md file distributed with
 * this work for additional information regarding copyright ownership.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.gwt.jso;

import java.util.Date;

import com.brazoft.foundation.gwt.client.i18n.DateFormat;
import com.google.gwt.core.client.*;
import com.google.gwt.json.client.JSONObject;

/**
 * To understand what is happening here, please visit the following address:
 * https://developers.google.com/web-toolkit/doc/latest/DevGuideCodingBasicsJSNI Please note that
 * primitive type long is disallowed.
 */
public abstract class JSObject
    extends JavaScriptObject {

	protected JSObject() {
		super();
	}

	public final native boolean hasKey(String key) /*-{
		try {
			return this[key] != undefined;
		} catch (error) {
			return false;
		}
	}-*/;

	public final native JsArrayString keys() /*-{
		var a = new Array();
		for ( var e in this) {
			a.push(e);
		}
		return a;
	}-*/;

	public final JSONObject json() {
		return new JSONObject(this);
	}

	public final native String get(String property) /*-{
		return this[property];
	}-*/;

	public final native int getInt(String property) /*-{
		return this[property];
	}-*/;

	public final native float getFloat(String property) /*-{
		return this[property];
	}-*/;

	public final native double getDouble(String property) /*-{
		return this[property];
	}-*/;

	public final native boolean getBoolean(String property) /*-{
		return this[property];
	}-*/;

	public final native <T extends JavaScriptObject> JsArray<T> getArray(String property) /*-{
		return this[property];
	}-*/;

	public final native <T extends JSObject> MapJSO<T> getMap(String property) /*-{
		return this[property];
	}-*/;

	public final Date getDate(String property) {
		return this.get(property, new Date(), DateFormat.ISO_8601);
	}

	public final String get(String property, String defaultValue) {
		if (!this.hasKey(property)) {
			return defaultValue;
		}

		return this.get(property);
	};

	public final Integer get(String property, Integer defaultValue) {
		if (!this.hasKey(property)) {
			return defaultValue;
		}

		return this.getInt(property);
	}

	public final int get(String property, int defaultValue) {
		if (!this.hasKey(property)) {
			return defaultValue;
		}

		return this.getInt(property);
	};

	public final Float get(String property, Float defaultValue) {
		if (!this.hasKey(property)) {
			return defaultValue;
		}

		return this.getFloat(property);
	}

	public final float get(String property, float defaultValue) {
		if (!this.hasKey(property)) {
			return defaultValue;
		}

		return this.getFloat(property);
	};

	public final Double get(String property, Double defaultValue) {
		if (!this.hasKey(property)) {
			return defaultValue;
		}

		return this.getDouble(property);
	}

	public final double get(String property, double defaultValue) {
		if (!this.hasKey(property)) {
			return defaultValue;
		}

		return this.getDouble(property);
	};

	public final Boolean get(String property, Boolean defaultValue) {
		if (!this.hasKey(property)) {
			return defaultValue;
		}

		return this.getBoolean(property);
	}

	public final boolean get(String property, boolean defaultValue) {
		if (!this.hasKey(property)) {
			return defaultValue;
		}

		return this.getBoolean(property);
	};

	public final <T extends JavaScriptObject> JsArray<T> get(String property, JsArray<T> defaultValue) {
		if (!this.hasKey(property)) {
			return defaultValue;
		}

		return this.getArray(property);
	};

	public final <T extends JSObject> MapJSO<T> get(String property, MapJSO<T> defaultValue) {
		if (!this.hasKey(property)) {
			return defaultValue;
		}

		return this.getMap(property);
	};

	public final <T extends JavaScriptObject> T get(String property, T defaultValue) {
		if (!this.hasKey(property)) {
			return defaultValue;
		}

		return this.getJavaScriptObject(property);
	}

	public final native <T extends JavaScriptObject> T getJavaScriptObject(String property) /*-{
		return this[property];
	}-*/;

	public final Date get(String property, Date defaultValue) {
		return this.get(property, defaultValue, DateFormat.ISO_8601);
	}

	public final Date get(String property, Date defaultValue, DateFormat format) {
		return format.unformat(this.get(property, format.format(defaultValue)));
	}

	public final native void set(String property, String value) /*-{
		this[property] = value;
	}-*/;

	public final native void set(String property, int value) /*-{
		this[property] = value;
	}-*/;

	public final native void set(String property, float value) /*-{
		this[property] = value;
	}-*/;

	public final native void set(String property, double value) /*-{
		this[property] = value;
	}-*/;

	public final native void set(String property, boolean value) /*-{
		this[property] = value;
	}-*/;

	public final native <T extends JavaScriptObject> void set(String property, JsArray<T> value) /*-{
		this[property] = value;
	}-*/;

	public final native <T extends JSObject> void set(String property, MapJSO<T> value) /*-{
		this[property] = value;
	}-*/;

	public final native <T extends JavaScriptObject> void set(String property, T value) /*-{
		this[property] = value;
	}-*/;

	public final void set(String property, Date value) {
		this.set(property, value, DateFormat.ISO_8601);
	}

	public final void set(String property, Date defaultValue, DateFormat format) {
		this.set(property, format.format(defaultValue));
	}
}