/**
 * Copyright (C) 2009-2012 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brazoft.foundation.gwt.client.i18n;

import com.brazoft.foundation.commons.Validator;
import com.brazoft.foundation.commons.format.api.Format;

public enum NumberFormat
    implements Format<Number> {
    INTEGER(getInteger()), CURRENCY(com.google.gwt.i18n.client.NumberFormat.getCurrencyFormat()), DECIMAL(com.google.gwt.i18n.client.NumberFormat.getDecimalFormat()), GLOBAL_CURRENCY(com.google.gwt.i18n.client.NumberFormat.getGlobalCurrencyFormat()), PERCENT(com.google.gwt.i18n.client.NumberFormat.getPercentFormat()), SCIENTIFIC(com.google.gwt.i18n.client.NumberFormat.getScientificFormat()), SIMPLE_CURRENCY(com.google.gwt.i18n.client.NumberFormat.getSimpleCurrencyFormat()), CUSTOM(null);

    private com.google.gwt.i18n.client.NumberFormat wrapped;

    private NumberFormat(com.google.gwt.i18n.client.NumberFormat wrapped) {
	this.wrapped = wrapped;
    }

    @Override
    public String format(Number value) {
	return value == null ? null : this.wrapped.format(value);
    }

    @Override
    public Number unformat(String value) {
	return Validator.isEmptyOrNull(value) ? null : this.wrapped.parse(value);
    }

    @Override
    public String pattern() {
	return this.wrapped.getPattern();
    }

    public static NumberFormat CUSTOM(String pattern) {
	NumberFormat instance = NumberFormat.CUSTOM;
	instance.wrapped = com.google.gwt.i18n.client.NumberFormat.getFormat(pattern);

	return instance;
    }

    static com.google.gwt.i18n.client.NumberFormat getInteger() {
	String pattern = com.google.gwt.i18n.client.NumberFormat.getDecimalFormat().getPattern();
	pattern = pattern.substring(0, pattern.lastIndexOf("."));

	return com.google.gwt.i18n.client.NumberFormat.getFormat(pattern);
    }
}
