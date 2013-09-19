package com.brazoft.foundation.gwt.client.ui.binding;

import com.brazoft.foundation.gwt.client.ui.api.Input;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.*;

public class PlaceholderHTML4
    implements Placeholder {

	private String lastValue   = "";
	private String placeholder = "";

	@Override
	public void apply(final Input<?, String> input, String placeholder) {
		this.placeholder = placeholder;

		// Coloca texto padrão
		input.value(placeholder);
		input.onFocus(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {
				if (input.getValue().equals(PlaceholderHTML4.this.placeholder)) {
					input.cursorAt(0);
				}
			}
		});
		input.onBlur(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				String value = input.getValue();
				if (!PlaceholderHTML4.this.lastValue.equals(value)) {
					PlaceholderHTML4.this.lastValue = value;
				}

				if (value.isEmpty()) {
					input.value(PlaceholderHTML4.this.placeholder);
				}
			}
		});
		input.onChange(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (input.getValue().equals(PlaceholderHTML4.this.placeholder)) {
					input.addStyleName("placeholder");
					return;
				}
				
				input.removeStyleName("placeholder");
			}
		});
	}
	
	@Override
	public String getValue(Input<?, String> input) {
	    String value = ((InputElement) input.getElement().cast()).getValue();
	    
	    if(this.placeholder.equals(value)){
	    	value = "";
	    }
	    
		return value;
	}
}