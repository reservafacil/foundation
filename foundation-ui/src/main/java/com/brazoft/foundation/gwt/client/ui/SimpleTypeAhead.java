package com.brazoft.foundation.gwt.client.ui;

public final class SimpleTypeAhead
    extends TypeAhead<SimpleTypeAhead, String> {

	@Override
	public SimpleTypeAhead clear() {
		this.input().clear();
		return this;
	}

	@Override
	public SimpleTypeAhead value(String value) {
		this.input().value(value);
		return this;
	}

	@Override
	public String getValue() {
		return this.input().getValue();
	}
}