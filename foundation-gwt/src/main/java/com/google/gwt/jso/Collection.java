package com.google.gwt.jso;

import java.util.Iterator;

import com.google.gwt.core.client.*;

public class Collection<J extends JavaScriptObject>
    implements Iterable<J>, Iterator<J> {

    private JsArray<J> array;

    int                index;

    public Collection(JsArray<J> array) {
	this.array = array;
    }

    @Override
    public Iterator<J> iterator() {
	this.index = 0;
	return this;
    }

    @Override
    public boolean hasNext() {
	boolean hasNext = index < array.length();
	if (!hasNext) {
	    this.index = 0;
	}

	return hasNext;
    }

    @Override
    public J next() {
	return this.array.get(this.index++);
    }

    @Override
    public void remove() {
	this.array.set(this.index, null);
    }

    public JsArray<J> array() {
	return this.array;
    }
}