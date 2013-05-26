package com.brazoft.foundation.soa;

import org.jboss.resteasy.logging.Logger;

import com.brazoft.foundation.commons.ResponseStatus;

@SuppressWarnings("unchecked")
public abstract class StatusResponse<S extends StatusResponse<S>> {

    private int status = ResponseStatus.SUCCESS.getCode();

    public S status(ResponseStatus status) {
	this.status = status.getCode();
	return (S)this;
    }

    public S status(Throwable e) {
	this.status(ResponseStatus.FAILURE);

	Logger.getLogger(this.getClass()).error("Returning response with the following error.", e);

	return (S)this;
    }

    public int getStatus() {
	return status;
    }
}