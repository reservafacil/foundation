package com.brazoft.foundation.commons.validator.api;

public interface Validator<T> {

  boolean validate(T value);

  String getMessage();
}