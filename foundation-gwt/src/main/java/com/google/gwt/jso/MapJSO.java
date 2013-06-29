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

import java.util.*;

import com.google.gwt.core.client.*;

public class MapJSO<J extends JavaScriptObject>
    extends JavaScriptObject {

    protected MapJSO() {

    }

    public static native <J extends JavaScriptObject> MapJSO<J> create()/*-{
	                                                                return new Object();
	                                                                }-*/;

    public final native boolean containsKey(String key) /*-{
	                                                return this[key] != undefined;
	                                                }-*/;

    public final native void put(String key, J value) /*-{
	                                              this[key] = value;
	                                              }-*/;

    protected final native JsArrayString keys() /*-{
	                                        var a = new Array();
	                                        for ( var e in this) {
	                                        a.push(e);
	                                        }
	                                        return a;
	                                        }-*/;

    public final native J get(String key) /*-{
	                                  return this[key];
	                                  }-*/;

    public final Set<String> keySet() {
	JsArrayString array = keys();
	Set<String> set = new HashSet<String>();
	for (int i = 0; i < array.length(); i++) {
	    set.add(array.get(i));
	}

	return set;
    }

    public final int size() {
	return this.keys().length();
    }

    public final boolean isEmpty() {
	return this.size() == 0;
    }

    public final J remove(String key) {
	J value = this.get(key);

	this.put(key, null);

	return value;
    }

    public final void putAll(MapJSO<J> map) {
	JsArrayString keys = this.keys();

	for (int idx = 0; idx < keys.length(); idx++) {
	    String key = keys.get(idx);
	    this.put(key, map.get(key));
	}
    }

    public final void clear() {
	for (String key : this.keySet()) {
	    this.remove(key);
	}
    }

    public final JsArray<J> values() {
	JsArray<J> values = JsArray.createArray().cast();

	for (String key : this.keySet()) {
	    values.push(this.get(key));
	}

	return values;
    }
}