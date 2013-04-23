package com.brazoft.foundation.commons.validator;

import com.brazoft.foundation.commons.Validator;
import com.brazoft.foundation.commons.validator.api.AbstractValidator;

public class NotEmptyValidator<T> extends AbstractValidator<NotEmptyValidator<T>, T>
{
	public NotEmptyValidator()
	{
		this.nullable(false);
	}
	
	@Override
	protected boolean delegateValidation(T value)
	{
		return !Validator.isEmptyOrNull(value);
	}
}