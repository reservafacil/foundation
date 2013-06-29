package com.brazoft.foundation.commons.validator;

import com.brazoft.foundation.commons.validator.api.AbstractValidator;

public class EqualsValidator<T> extends AbstractValidator<EqualsValidator<T>, T> {

  private T value;

  public EqualsValidator(T value) {
    this.value = value;
  }

  @Override
  public boolean delegateValidation(T value) {
    if (this.value != null) {
      return this.value.equals(value);
    }

    return this.value == null && value == null;
  }
}