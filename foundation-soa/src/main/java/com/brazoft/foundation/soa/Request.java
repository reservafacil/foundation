package com.brazoft.foundation.soa;

public class Request<T> {

    private T   data;

    private int start;

    private int end;

    public T getData() {
	return data;
    }

    public void setData(T data) {
	this.data = data;
    }

    public int getStart() {
	return start;
    }

    public void setStart(int start) {
	this.start = start;
    }

    public int getEnd() {
	return end;
    }

    public void setEnd(int end) {
	this.end = end;
    }
}
