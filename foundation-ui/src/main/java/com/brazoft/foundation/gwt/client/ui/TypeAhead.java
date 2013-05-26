package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.json.JSONCollection;
import com.brazoft.foundation.gwt.client.ui.api.DecoratedInput;

@SuppressWarnings("unchecked")
public abstract class TypeAhead<T extends TypeAhead<T, V>, V>
    extends DecoratedInput<T, V> {

    public TypeAhead() {
	super(new InputText());
	this.init();
    }

    private void init() {
	this.input().attribute("data-provide", "typeahead");
    }
    
    @Override
    public InputText input() {
        return (InputText) super.input();
    }
    
    public T block() {
	this.input().block();
	return (T)this;
    }
    
    public T source(JSONCollection<String> source) {
	this.input().attribute("data-source", source.toString());
	return (T) this;
    }
    
    public T visibleItems(int numberOfItems)
    {
	this.input().attribute("data-items", String.valueOf(numberOfItems));
	return (T) this;
    }
    
    public T triggerAutoComplete(int minLength)
    {
	this.input().attribute("data-items", String.valueOf(minLength));
	return (T) this;
    }
}