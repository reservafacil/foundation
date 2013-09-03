package com.google.gwt.jso;

import com.brazoft.foundation.commons.ResponseStatus;

public class StatusResponseJSO
    extends JSObject {

	private static final String STATUS = "status";
	
	private static final String MESSAGECODE = "messageCode";

	protected StatusResponseJSO() {}

	public final ResponseStatus status() {
		return ResponseStatus.valueOf(this.getInt(STATUS));
	}
	
	public final String messageCode() {
		return this.get(MESSAGECODE);
	}
}