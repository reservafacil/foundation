package com.brazoft.foundation.commons.validator;

import com.brazoft.foundation.commons.validator.api.AbstractValidator;

public class LengthValidator extends AbstractValidator<LengthValidator, String>
{
	private Integer	min;

	private Integer	max;

	public LengthValidator()
	{
		this(0, Integer.MAX_VALUE);
	}

	public LengthValidator(Integer min, Integer max)
	{
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean delegateValidation(String value)
	{
		Integer length = value.length();

		return length >= this.min && length <= this.max;
	}
}