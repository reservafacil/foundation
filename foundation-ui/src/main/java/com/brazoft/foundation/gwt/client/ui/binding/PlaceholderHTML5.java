package com.brazoft.foundation.gwt.client.ui.binding;

import com.brazoft.foundation.gwt.client.ui.api.Input;
import com.google.gwt.dom.client.InputElement;

public class PlaceholderHTML5 implements Placeholder {

	public void apply(Input<?, String> input, String placeholder) {
		input.attribute("placeholder", placeholder);
	}
	
	@Override
	public String getValue(Input<?, String> input) {
	    return ((InputElement) input.getElement().cast()).getValue();
	}
}