package com.brazoft.foundation.commons.validator.api;

@SuppressWarnings("unchecked")
public abstract class AbstractValidator<V extends Validator<T>, T> implements Validator<T> {

  private boolean nullable = true;

  private String message;

  public V message(String message) {
    this.message = message;
    return (V) this;
  }

  public String getMessage() {
    return message;
  }

  public V nullable(boolean nullable) {
    this.nullable = nullable;
    return (V) this;
  }

  public boolean validate(T value) {
    if (value == null) {
      return nullable;
    }

    try {
      return this.delegateValidation(value);
    } catch (RuntimeException e) {
      return false;
    }
  }

  protected abstract boolean delegateValidation(T value);
}