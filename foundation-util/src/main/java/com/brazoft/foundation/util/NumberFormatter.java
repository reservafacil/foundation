package com.brazoft.foundation.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public enum NumberFormatter {
    DEFAULT, CURRENCY, NUMBER, INTEGER, PERCENT;

    public String toString(Number value, Locale locale) {
	return this.getFormat(locale).format(value);
    }

    public String toString(Number value) {
	return this.toString(value, Locale.getDefault());
    }

    public Number toNumber(String value) {
	return this.toNumber(value, Locale.getDefault());
    }

    public Number toNumber(String value, Locale locale) {
	try {
	    return this.getFormat(locale).parse(value);
	} catch (ParseException e) {
	    ExceptionHandler.handleRuntime(e);
	    return null;
	}
    }

    private NumberFormat getFormat(Locale locale) {
	switch (this) {
	    case CURRENCY:
		return NumberFormat.getCurrencyInstance(locale);
	    case INTEGER:
		return NumberFormat.getIntegerInstance(locale);
	    case NUMBER:
		return NumberFormat.getNumberInstance(locale);
	    case PERCENT:
		return NumberFormat.getPercentInstance(locale);
	}

	return NumberFormat.getInstance(locale);
    }
}