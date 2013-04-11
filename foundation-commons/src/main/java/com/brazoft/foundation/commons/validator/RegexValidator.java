package com.brazoft.foundation.commons.validator;

import com.brazoft.foundation.commons.validator.api.AbstractValidator;

public class RegexValidator extends AbstractValidator<RegexValidator, String>
{
	private String regex;
	
	public RegexValidator(String regex)
	{
		this.regex = regex;
	}

	@Override
	public boolean delegateValidation(String value)
	{
		return value.matches(this.regex);
	}
}