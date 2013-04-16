package com.brazoft.foundation.commons.validator;

import com.brazoft.foundation.commons.validator.api.AbstractValidator;

public class IntegerValidator extends AbstractValidator<IntegerValidator, String>
{
	private Integer	min;

	private Integer	max;

	public IntegerValidator()
	{
		this(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public IntegerValidator(Integer min, Integer max)
	{
		super();
		this.min = min;
		this.max = max;
	}

	public boolean delegateValidation(String value)
	{
		return Integer.valueOf(value) >= this.min && Integer.valueOf(value) <= this.max;
	}
}