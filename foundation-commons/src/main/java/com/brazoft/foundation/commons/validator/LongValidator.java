package com.brazoft.foundation.commons.validator;

import com.brazoft.foundation.commons.validator.api.AbstractValidator;

public class LongValidator extends AbstractValidator<LongValidator, String> {

  private Long min;

  private Long max;

  public LongValidator() {
    super();
  }

  public LongValidator(Long min, Long max) {
    super();
    this.min = Long.MIN_VALUE;
    this.max = Long.MAX_VALUE;
  }

  protected boolean delegateValidation(String value) {
    return Long.valueOf(value) >= this.min && Long.valueOf(value) <= this.max;
  }
}