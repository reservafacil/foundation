package com.brazoft.foundation.soa;

import java.util.*;

import org.jboss.resteasy.logging.Logger;

import com.brazoft.foundation.commons.ResponseStatus;

public class Response<V>
    extends StatusResponse<Response<V>> {

    private List<V> data = new ArrayList<V>();

    private int     start;

    private int     end;

    private long    total;

    public Response<V> addAll(Collection<? extends V> values) {
	this.end += values.size();
	this.data.addAll(values);
	return this;
    }

    public Response<V> add(V value) {
	this.end++;
	this.data.add(value);
	return this;
    }

    public List<V> getData() {
	return data;
    }

    public int getStart() {
	return start;
    }

    public int getEnd() {
	return this.end + this.start;
    }

    public Response<V> start(int start) {
	this.start = start;
	return this;
    }

    public long getTotal() {
	return total;
    }

    public Response<V> total(long total) {
	this.total = total;
	return this;
    }

    public static <V> Response<V> handle(Throwable e) {
	Response<V> response = new Response<V>();
	response.status(ResponseStatus.FAILURE);

	Logger.getLogger(Response.class).error("Returning response with the following error.", e);

	return response;
    }
}