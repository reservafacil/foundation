package com.brazoft.foundation.commons.validator;

import com.brazoft.foundation.commons.format.api.Format;
import com.brazoft.foundation.commons.validator.api.AbstractValidator;

public class FormatValidator<V>
    extends AbstractValidator<FormatValidator<V>, String> {
    
    private Format<V> format;

    public FormatValidator(Format<V> format) {
	super();
	this.format = format;
    }

    @Override
    protected boolean delegateValidation(String value) {
	try {
	    this.format.unformat(value);
	    return true;
	} catch (IllegalArgumentException e) {
	    return false;
	}
    }
}
