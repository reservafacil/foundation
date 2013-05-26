package com.brazoft.foundation.soa;

import org.jboss.resteasy.logging.Logger;

import com.brazoft.foundation.commons.ResponseStatus;

public class SingleResponse<V>
    extends StatusResponse<SingleResponse<V>> {

    private V data;

    public SingleResponse<V> data(V data) {
	this.data = data;
	return this;
    }

    public V getData() {
	return data;
    }

    public static <V> SingleResponse<V> handle(Throwable e) {
	SingleResponse<V> response = new SingleResponse<V>();
	response.status(ResponseStatus.FAILURE);

	Logger.getLogger(SingleResponse.class).error("Returning response with the following error.", e);

	return response;
    }
}