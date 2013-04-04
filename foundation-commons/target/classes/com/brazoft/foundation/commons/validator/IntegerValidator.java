package com.brazoft.foundation.commons.validator;

import com.brazoft.foundation.commons.validator.api.AbstractValidator;

public class IntegerValidator extends AbstractValidator<IntegerValidator, String>
{
	private Integer	min;

	private Integer	max;

	public IntegerValidator()
	{
		super();
	}

	public IntegerValidator(Integer min, Integer max)
	{
		super();
		this.min = Integer.MIN_VALUE;
		this.max = Integer.MAX_VALUE;
	}

	public boolean delegateValidation(String value)
	{
		return Integer.valueOf(value) >= this.min && Integer.valueOf(value) <= this.max;
	}
}