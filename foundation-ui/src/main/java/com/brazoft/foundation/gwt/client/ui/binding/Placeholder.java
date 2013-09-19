package com.brazoft.foundation.gwt.client.ui.binding;

import com.brazoft.foundation.gwt.client.ui.api.Input;

public interface Placeholder {
	void apply(Input<?, String> input, String placeholder);
	
	String getValue(Input<?, String> input);
}