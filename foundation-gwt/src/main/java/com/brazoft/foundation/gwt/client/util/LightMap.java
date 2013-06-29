package com.brazoft.foundation.gwt.client.util;

import com.google.gwt.jso.*;

public class LightMap
    extends JSObject {

    protected LightMap() {}

    public static final native LightMap create()/*-{
	                                        return new Object();
	                                        }-*/;

    public final native int size() /*-{
	                           return this.length;
	                           }-*/;

    public final native boolean isEmpty() /*-{
	                                  return this.length == 0;
	                                  }-*/;

    public final native boolean containsValue(String value) /*-{
	                                                    for ( var key in this) {
	                                                    if (this[key] === value) {
	                                                    return true;
	                                                    }
	                                                    }

	                                                    return false;
	                                                    }-*/;

    public final native LightMap remove(String key) /*-{
	                                            delete this[key];
	                                            return this;
	                                            }-*/;

    public final native LightMap putAll(LightMap map) /*-{
	                                              for ( var key in map) {
	                                              this[key] = map[key];
	                                              }
	                                              
	                                              return this;
	                                              }-*/;

    public final LightMap putAll(Collection<Entry> entries) {
	for (Entry entry : entries) {
	    this.set(entry.getKey(), entry.getValue());
	}

	return this;
    }

    public final native LightMap clear() /*-{
	                                 for (var key in this) {
	                                 delete this[key];
	                                 }
	                                 
	                                 return this;
	                                 }-*/;
}