package com.brazoft.foundation.gwt.client.util;

public interface Command<T> {

    void execute(T value);
}